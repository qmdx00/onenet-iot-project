package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
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

import javax.servlet.http.HttpServletRequest;
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

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService, AccountService accountService, TokenUtil tokenUtil) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 客户注册
     *
     * @param name     用户名
     * @param password 密码
     * @param phone    电话
     * @param email    邮箱
     * @param addr     地址
     * @return Response
     */
    @PostMapping
    public Response createCustomer(@RequestParam("name") String name,
                                   @RequestParam("password") String password,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("email") String email,
                                   @RequestParam("addr") String addr) {

        if (!VerifyUtil.checkString(name, password, phone, email, addr)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            Account posted = accountService.findByName(name);
            if (posted == null) {
                String id = UUIDUtil.getUUID();
                // 构建客户对象
                Customer customer = Customer.builder()
                        .customerId(id)
                        .name(name)
                        .phone(phone)
                        .email(email)
                        .addr(addr)
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
     * 管理员通过 客户ID 获取客户信息
     *
     * @param request 请求
     * @param id      用户 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getCustomerByAdmin(HttpServletRequest request,
                                       @PathVariable String id) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(id, token)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String adminId = claim.asString();
                Account account = accountService.findAccountById(adminId);
                // 判断角色是否有权限
                if (account != null && account.getRole() == Role.ADMIN) {
                    Customer customer = customerService.findCustomerById(id);
                    if (customer != null) {
                        log.info("customer: {}", customer);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, customer);
                    } else {
                        return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
                    }
                } else {
                    return ResultUtil.returnStatus(ResponseStatus.VISITED_FORBID);
                }
            } catch (JWTVerificationException e) {
                // 解析失败，token无效
                log.error("{}", e);
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
            }
        }
    }

    /**
     * 通过 token 获取客户信息
     *
     * @param request 请求
     * @return Response
     */
    @GetMapping
    public Response getCustomer(HttpServletRequest request) {
        
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                // 判断角色是否有权限
                if (account != null) {
                    Customer customer = customerService.findCustomerById(customerId);
                    if (customer != null) {
                        log.info("customer: {}", customer);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, customer);
                    } else {
                        return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
                    }
                } else {
                    return ResultUtil.returnStatus(ResponseStatus.VISITED_FORBID);
                }
            } catch (JWTVerificationException e) {
                // 解析失败，token无效
                log.error("{}", e);
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
            }
        }
    }

    /**
     * 通过客户 token 修改客户信息
     *
     * @param request 请求
     * @param name    姓名
     * @param phone   电话
     * @param email   邮箱
     * @param addr    地址
     * @return Response
     */
    @PutMapping
    public Response updateCustomer(HttpServletRequest request,
                                   @RequestParam("name") String name,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("email") String email,
                                   @RequestParam("addr") String addr) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, name, phone, email, addr)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                // 判断角色是否有权限
                if (account != null) {
                    Customer customer = customerService.findCustomerById(customerId);
                    if (customer == null) {
                        return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
                    } else {
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                                MapUtil.create("row", customerService.updateCustomer(customerId, name, phone, email, addr) + ""));
                    }
                } else {
                    return ResultUtil.returnStatus(ResponseStatus.VISITED_FORBID);
                }
            } catch (JWTVerificationException e) {
                // 解析失败，token无效
                log.error("{}", e);
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
            }
        }
    }

    /**
     * 通过客户 token 删除客户信息
     *
     * @param request 请求
     * @return Response
     */
    @DeleteMapping
    public Response deleteCustomer(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String customerId = claim.asString();
                Account account = accountService.findAccountById(customerId);
                // 判断角色是否有权限
                if (account != null) {
                    Integer row = customerService.deleteCustomer(customerId);
                    log.info("delete customer: {}", row);
                    return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                            MapUtil.create("row", row + ""));
                } else {
                    return ResultUtil.returnStatus(ResponseStatus.VISITED_FORBID);
                }
            } catch (JWTVerificationException e) {
                // 解析失败，token无效
                log.error("{}", e);
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
            }
        }
    }
}
