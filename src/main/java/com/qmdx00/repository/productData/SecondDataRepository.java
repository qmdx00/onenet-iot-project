package com.qmdx00.repository.productData;

import com.qmdx00.entity.productData.SecondData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/20 20:07
 * @description 第二道工序数据持久化
 */
@Repository
public interface SecondDataRepository extends JpaRepository<SecondData, String> {
}
