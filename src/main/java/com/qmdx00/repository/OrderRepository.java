package com.qmdx00.repository;

import com.qmdx00.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:51
 * @description 订单持久化
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
