package com.qmdx00.controller;

import com.qmdx00.entity.Account;
import com.qmdx00.entity.Admin;
import com.qmdx00.service.AdminService;
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
 * @date 19/06/17 08:35
 * @description 管理员 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController extends BaseController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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
        }
    }

    /**
     * 通过 ID 产找管理员信息
     *
     * @param id 管理员 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getCustomerById(@PathVariable String id) {
        if (VerifyUtil.checkString(id)) {
            Admin admin = adminService.findAdminById(id);
            if (admin == null) {
                return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
            } else {
                log.info("find admin: {}", admin);
                return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, admin);
            }
        } else {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        }
    }

    /**
     * 通过 ID 修改管理员信息
     *
     * @param id    管理员 ID
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
            Admin admin = adminService.findAdminById(id);
            if (admin == null) {
                return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
            } else {
                return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS,
                        MapUtil.create("row", adminService.updateAdmin(id, name, phone, email) + ""));
            }
        }
    }

    /**
     * 通过 ID 删除管理员信息
     *
     * @param id 管理员 ID
     * @return Response
     */
    @DeleteMapping("/{id}")
    public Response deleteCustomerById(@PathVariable String id) {
        if (VerifyUtil.checkString(id)) {
            Integer row = adminService.deleteAdmin(id);
            log.info("delete customer: {}", row);
            return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, MapUtil.create("row", row + ""));
        } else {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        }
    }
}
