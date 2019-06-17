package com.qmdx00.service;

import com.qmdx00.entity.ProductData;

/**
 * @author yuanweimin
 * @date 19/06/17 20:25
 * @description 产品生产过程数据逻辑
 */
public interface ProductDataService {

    /**
     * 保存产品生产过程数据
     *
     * @param data 产品数据
     * @return ProductData
     */
    ProductData saveProductData(ProductData data);
}
