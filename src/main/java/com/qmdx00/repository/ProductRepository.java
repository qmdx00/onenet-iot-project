package com.qmdx00.repository;

import com.qmdx00.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:52
 * @description 产品信息持久化
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
