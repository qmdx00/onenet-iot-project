package com.qmdx00.service;

import java.util.HashMap;

/**
 * @author yuanweimin
 * @date 19/06/22 17:37
 * @description 生产过程数据查询逻辑
 */
public interface ProductDataService {
    /**
     * 通过产品ID查询生产过程数据
     *
     * @param productId 产品 ID
     * @return HashMap
     */
    HashMap getData(String productId);
}
