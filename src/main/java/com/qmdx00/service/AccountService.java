package com.qmdx00.service;

import com.qmdx00.entity.Account;

/**
 * @author yuanweimin
 * @date 19/06/11 23:08
 * @description 账户服务逻辑
 */
public interface AccountService {
    /**
     * 通过 ID 查找账户
     *
     * @param id 账户 ID
     * @return Account
     */
    Account findAccountById(String id);

    /**
     * 通过用户名和密码登录
     *
     * @param name     用户名
     * @param password 密码
     * @return Account
     */
    Account findAccountByNameAndPassword(String name, String password);
}
