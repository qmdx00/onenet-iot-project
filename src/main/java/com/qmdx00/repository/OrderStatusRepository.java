package com.qmdx00.repository;

import com.qmdx00.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/22 12:05
 * @description 订单状态持久化
 */
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {

    /**
     * 通过订单 ID 修改订单状态
     *
     * @param orderId 订单 ID
     * @param status  订单状态
     * @return Integer
     */
    @Modifying
    @Query(value = "update t_order_status as s set s.order_status = ?2 where s.order_id = ?1", nativeQuery = true)
    Integer updateOrderStatus(String orderId, String status);
}
