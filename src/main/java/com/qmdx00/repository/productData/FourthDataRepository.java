package com.qmdx00.repository.productData;

import com.qmdx00.entity.productData.FourthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/20 20:09
 * @description 第四道工序数据持久化
 */
@Repository
public interface FourthDataRepository extends JpaRepository<FourthData, String> {
}
