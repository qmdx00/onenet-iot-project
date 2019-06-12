package com.qmdx00.service;

import com.qmdx00.entity.Account;
import com.qmdx00.entity.Customer;
import com.qmdx00.util.enums.ResponseStatus;

/**
 * @author yuanweimin
 * @date 19/06/10 17:23
 * @description 客户服务逻辑
 */
public interface CustomerService {
    /**
     * 通过 ID 查找客户信息
     *
     * @param id 客户 ID
     * @return Customer
     */
    Customer findCustomerById(String id);

    /**
     * 新增客户信息
     *
     * @param customer 客户
     * @param account  账号
     * @return ResponseStatus
     */
    ResponseStatus saveCustomer(Customer customer, Account account);

    /**
     * 通过 ID 修改客户信息
     *
     * @param id    客户 ID
     * @param name  姓名
     * @param phone 手机
     * @param email 邮箱
     * @return Integer
     */
    Integer updateCustomer(String id, String name, String phone, String email);

    /**
     * 通过 ID 删除客户信息
     *
     * @param id 客户 ID
     * @return Integer
     */
    Integer deleteCustomer(String id);
}
