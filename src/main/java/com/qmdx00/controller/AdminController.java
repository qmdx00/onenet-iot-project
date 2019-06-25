package com.qmdx00.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.qmdx00.entity.Account;
import com.qmdx00.entity.Admin;
import com.qmdx00.service.AccountService;
import com.qmdx00.service.AdminService;
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
 * @date 19/06/17 08:35
 * @description 管理员 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController extends BaseController {

    private final TokenUtil tokenUtil;
    private final AccountService accountService;
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService, AccountService accountService, TokenUtil tokenUtil) {
        this.adminService = adminService;
        this.accountService = accountService;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 管理员注册
     *
     * @param name     管理员名
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
            Account posted = accountService.findByName(name);
            if (posted == null) {
                String id = UUIDUtil.getUUID();
                Admin admin = Admin.builder()
                        .adminId(id)
                        .name(name)
                        .email(email)
                        .phone(phone)
                        .createTime(new Date())
                        .updateTime(new Date())
                        .build();
                Account account = Account.builder()
                        .id(id)
                        .name(name)
                        .password(EncryptionUtil.encrypt(password))
                        .role(Role.ADMIN)
                        .build();
                log.info("saved admin: {}, saved account: {}", admin, account);
                return ResultUtil.returnStatusAndData(adminService.saveAdmin(admin, account), MapUtil.create("id", id));
            } else {
                return ResultUtil.returnStatus(ResponseStatus.UPDATE_FAILED, "账号已存在");
            }
        }
    }

    /**
     * 通过 token 查找管理员信息
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
                String adminId = claim.asString();
                Account account = accountService.findAccountById(adminId);
                // 判断角色是否有权限
                if (account != null) {
                    Admin admin = adminService.findAdminById(adminId);
                    if (admin != null) {
                        log.info("admin: {}", admin);
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, admin);
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
     * 通过 token 修改管理员信息
     *
     * @param request 请求
     * @param name    姓名
     * @param email   邮箱
     * @param phone   电话
     * @return Response
     */
    @PutMapping
    public Response updateCustomer(HttpServletRequest request,
                                       @RequestParam("name") String name,
                                       @RequestParam("phone") String phone,
                                       @RequestParam("email") String email) {

        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token, name, phone, email)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String adminId = claim.asString();
                Account account = accountService.findAccountById(adminId);
                // 判断角色是否有权限
                if (account != null) {
                    Admin admin = adminService.findAdminById(adminId);
                    if (admin == null) {
                        return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
                    } else {
                        return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                                MapUtil.create("row", adminService.updateAdmin(adminId, name, phone, email) + ""));
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
     * 通过 token 删除管理员信息
     *
     * @param request 请求
     * @return Response
     */
    @DeleteMapping
    public Response deleteCustomerById(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!VerifyUtil.checkString(token)) {
            return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN);
        } else {
            try {
                // 解析token
                Claim claim = tokenUtil.getClaim(token, "account_id");
                String adminId = claim.asString();
                Account account = accountService.findAccountById(adminId);
                // 判断角色是否有权限
                if (account != null) {
                    Integer row = adminService.deleteAdmin(adminId);
                    log.info("delete customer: {}", row);
                    return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, MapUtil.create("row", row + ""));
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
