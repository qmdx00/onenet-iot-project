package com.qmdx00.service;

/**
 * @author yuanweimin
 * @date 19/06/11 23:08
 * @description 账户服务逻辑
 */
public interface AccountService {

    /**
     * 通过用户名和密码登录
     *
     * @param name     用户名
     * @param password 密码
     * @return String
     */
    String accountLogin(String name, String password);
}
