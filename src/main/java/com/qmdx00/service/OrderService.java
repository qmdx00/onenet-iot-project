package com.qmdx00.service;

import com.qmdx00.entity.Order;
import com.qmdx00.util.enums.ResponseStatus;

import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/17 14:52
 * @description 订单逻辑
 */
public interface OrderService {

    /**
     * 通过 ID 查找订单
     *
     * @param id         订单 ID
     * @param customerId 客户 ID
     * @return Order
     */
    Order findOrderById(String id, String customerId);

    /**
     * 查找指定客户的所有订单
     *
     * @param customerId 客户 ID
     * @return List
     */
    List<Order> findAllOrderByCustomer(String customerId);

    /**
     * 管理员获取所有客户订单
     *
     * @return List
     */
    List<Order> findAllOrderByAdmin();

    /**
     * 创建一条订单
     *
     * @param order 订单实体
     * @return ResponseStatus
     */
    ResponseStatus saveOrder(Order order);

    /**
     * 修改订单
     *
     * @param order 订单实体
     * @return Integer
     */
    Integer updateOrder(Order order);
}
