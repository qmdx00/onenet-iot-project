package com.qmdx00.controller;

import com.qmdx00.entity.Account;
import com.qmdx00.entity.Customer;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.CustomerService;
import com.qmdx00.util.*;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.enums.Role;
import com.qmdx00.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/10 15:38
 * @description 客户接口 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController extends BaseController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    /**
     * 客户注册
     *
     * @param name     用户名
     * @param password 密码
     * @param email    邮箱
     * @param phone    电话
     * @return Response
     */
    @PostMapping
    public Response createCustomer(@RequestParam("name") String name,
                                   @RequestParam("password") String password,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone) {

        if (!VerifyUtil.checkString(name, password, email, phone)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            Account posted = accountService.findByNameAndPassword(name, EncryptionUtil.encrypt(password));
            if (posted == null) {
                String id = UUIDUtil.getUUID();
                // 构建客户对象
                Customer customer = Customer.builder()
                        .customerId(id)
                        .name(name)
                        .phone(phone)
                        .email(email)
                        .createTime(new Date())
                        .updateTime(new Date())
                        .build();
                // 构建账户实体
                Account account = Account.builder()
                        .id(id)
                        .name(name)
                        .password(EncryptionUtil.encrypt(password))
                        .role(Role.USER)
                        .build();
                log.info("saved customer: {}, saved account: {}", customer, account);
                return ResultUtil.returnStatusAndData(customerService.saveCustomer(customer, account), MapUtil.create("id", id));
            } else {
                return ResultUtil.returnStatus(ResponseStatus.UPDATE_FAILED, "账号已存在");
            }
        }
    }

    /**
     * 通过 Id 获取客户信息
     *
     * @param id 客户 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getCustomerById(@PathVariable String id) {
        if (VerifyUtil.checkString(id)) {
            Customer customer = customerService.findCustomerById(id);
            if (customer == null) {
                return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
            } else {
                log.info("find customer: {}", customer);
                return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, customer);
            }
        } else {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        }
    }

    /**
     * 通过客户 ID 修改客户信息
     *
     * @param id    客户 ID
     * @param name  姓名
     * @param email 邮箱
     * @param phone 电话
     * @return Response
     */
    @PutMapping("/{id}")
    public Response updateCustomerById(@PathVariable String id,
                                       @RequestParam("name") String name,
                                       @RequestParam("phone") String phone,
                                       @RequestParam("email") String email) {

        if (!VerifyUtil.checkString(id, name, phone, email)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            Customer customer = customerService.findCustomerById(id);
            if (customer == null) {
                return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
            } else {
                return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                        MapUtil.create("row", customerService.updateCustomer(id, name, phone, email) + ""));
            }
        }

    }

    /**
     * 通过客户 ID 删除客户信息
     *
     * @param id 客户 ID
     * @return Response
     */
    @DeleteMapping("/{id}")
    public Response deleteCustomerById(@PathVariable String id) {
        if (VerifyUtil.checkString(id)) {
            Integer row = customerService.deleteCustomer(id);
            log.info("delete customer: {}", row);
            return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                    MapUtil.create("row", row + ""));
        } else {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        }
    }
}
