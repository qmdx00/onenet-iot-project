package com.qmdx00.service;

import com.qmdx00.entity.Product;

/**
 * @author yuanweimin
 * @date 19/06/17 19:51
 * @description 产品信息逻辑
 */
public interface ProductService {

    /**
     * 通过产品 ID 查找产品信息
     *
     * @param id 产品 ID
     * @return Product
     */
    Product findProductById(String id);

    /**
     * 创建产品信息
     *
     * @param product 产品信息
     * @return Product
     */
    Product saveProduct(Product product);
}
