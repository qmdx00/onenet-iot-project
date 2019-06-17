package com.qmdx00.service;

import com.qmdx00.entity.Producible;
import com.qmdx00.util.enums.ResponseStatus;

import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/17 16:33
 * @description 产品逻辑
 */
public interface ProducibleService {
    /**
     * 获取所有可生产产品列表
     *
     * @return List
     */
    List<Producible> findAllProducible();

    /**
     * 通过 ID 获取可生产产品信息
     *
     * @param id 产品 ID
     * @return Producible
     */
    Producible findProducibleById(String id);

    /**
     * 创建一条可生产产品信息
     *
     * @param producible 可生产产品
     * @return ResponseStatus
     */
    ResponseStatus saveProducible(Producible producible);

    /**
     * 通过 ID 删除可生产产品信息
     *
     * @param id 可生产产品 ID
     * @return Integer
     */
    Integer deleteProducibleById(String id);
}
