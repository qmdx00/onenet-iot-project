package com.qmdx00.repository.productData;

import com.qmdx00.entity.productData.FirstData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author yuanweimin
 * @date 19/06/20 20:06
 * @description 第一道工序数据持久化
 */
@Repository
public interface FirstDataRepository extends JpaRepository<FirstData, String> {

    /**
     * 通过产品ID产找工序数据
     *
     * @param productId 产品 ID
     * @return Optional
     */
    Optional<FirstData> findByProductId(String productId);
}
