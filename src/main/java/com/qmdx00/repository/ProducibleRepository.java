package com.qmdx00.repository;

import com.qmdx00.entity.Producible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 16:54
 * @description 可生产的产品信息持久化
 */
@Repository
public interface ProducibleRepository extends JpaRepository<Producible, String> {

}
