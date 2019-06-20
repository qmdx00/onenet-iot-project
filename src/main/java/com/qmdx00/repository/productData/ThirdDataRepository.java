package com.qmdx00.repository.productData;

import com.qmdx00.entity.productData.ThirdData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/20 20:08
 * @description 第三道工序数据持久化
 */
@Repository
public interface ThirdDataRepository extends JpaRepository<ThirdData, String> {
}
