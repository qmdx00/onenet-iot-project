package com.qmdx00.service;

import com.qmdx00.entity.Handle;

import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/22 10:26
 * @description 管理员订单处理逻辑
 */
public interface HandleService {

    /**
     * 获取所有处理记录
     *
     * @return List
     */
    List<Handle> getAllHandle();

    /**
     * 通过订单ID获取处理记录
     *
     * @param orderId 订单ID
     * @return Handle
     */
    Handle getHandle(String orderId);

    /**
     * 创建一条订单处理记录，默认为未处理状态
     *
     * @param handle 订单处理记录
     * @return Handle
     */
    Handle insertHandle(Handle handle);
}
