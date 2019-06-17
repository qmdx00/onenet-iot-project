package com.qmdx00.repository;

import com.qmdx00.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:53
 * @description 产品生产过程数据持久化
 */
@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, String> {
    
}
