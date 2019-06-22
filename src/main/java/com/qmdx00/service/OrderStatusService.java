package com.qmdx00.service;

import com.qmdx00.entity.OrderStatus;

/**
 * @author yuanweimin
 * @date 19/06/22 13:09
 * @description 订单状态逻辑
 */
public interface OrderStatusService {

    /**
     * 通过订单 ID 获取订单状态
     *
     * @param orderId 订单 ID
     * @return OrderStatus
     */
    OrderStatus getStatusById(String orderId);

    /**
     * 创建订单状态记录
     *
     * @param status 状态
     * @return OrderStatus
     */
    OrderStatus saveStatus(OrderStatus status);

    /**
     * 修改订单状态
     *
     * @param status 状态
     * @return Integer
     */
    Integer updateStatus(OrderStatus status);
}
