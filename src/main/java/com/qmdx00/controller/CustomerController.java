package com.qmdx00.controller;

import com.qmdx00.entity.Account;
import com.qmdx00.entity.Customer;
import com.qmdx00.service.CustomerService;
import com.qmdx00.util.EncryptionUtil;
import com.qmdx00.util.ResultUtil;
import com.qmdx00.util.UUIDUtil;
import com.qmdx00.util.VerifyUtil;
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

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Response createCustomer(@RequestParam("name") String name,
                                   @RequestParam("password") String password,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone) {

        if (!VerifyUtil.checkString(name, password, email, phone)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            String id = UUIDUtil.getUUID();
            // 构建客户对象
            Customer customer = Customer.builder()
                    .customerId(id)
                    .name(name)
                    .phone(phone)
                    .email(email)
                    .createTime(new Date())
                    .build();
            // 构建账户实体
            Account account = Account.builder()
                    .id(id)
                    .name(name)
                    .password(EncryptionUtil.encrypt(password))
                    .role(Role.USER)
                    .build();
            log.info("saved customer: {}, saved account: {}", customer, account);
            return ResultUtil.returnStatus(customerService.saveCustomer(customer, account));
        }
    }

    @GetMapping("/{id}")
    public void getCustomerById(@PathVariable String id) {
        log.info("{}", id);
    }

    @PutMapping("/{id}")
    public void updateCustomerById(@PathVariable String id) {

    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable String id) {

    }
}
