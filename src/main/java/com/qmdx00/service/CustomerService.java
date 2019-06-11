package com.qmdx00.service;

import com.qmdx00.entity.Customer;

/**
 * @author yuanweimin
 * @date 19/06/10 17:23
 * @description 客户服务逻辑
 */
public interface CustomerService {
    /**
     * 通过 id 查找客户信息
     *
     * @param id 客户id
     * @return Customer
     */
    Customer findCustomerById(String id);

    /**
     * 新增客户信息
     *
     * @param customer 客户
     * @return Customer
     */
    Customer saveCustomer(Customer customer);
}
