package com.qmdx00.service;

import com.qmdx00.entity.Account;
import com.qmdx00.entity.Admin;
import com.qmdx00.util.enums.ResponseStatus;

/**
 * @author yuanweimin
 * @date 19/06/17 08:58
 * @description 管理员服务接口
 */
public interface AdminService {
    /**
     * 通过 ID 查找管理员信息
     *
     * @param id 管理员 ID
     * @return Admin
     */
    Admin findAdminById(String id);

    /**
     * 新增管理员信息
     *
     * @param admin   管理员
     * @param account 账户
     * @return ResponseStatus
     */
    ResponseStatus saveAdmin(Admin admin, Account account);

    /**
     * 修改管理员信息
     *
     * @param id    管理员 ID
     * @param name  管理员姓名
     * @param phone 手机号
     * @param email 邮箱
     * @return Integer
     */
    Integer updateAdmin(String id, String name, String phone, String email);

    /**
     * 通过 ID 删除管理员信息和账户
     *
     * @param id 管理员 ID
     * @return Integer
     */
    Integer deleteAdmin(String id);
}
