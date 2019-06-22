package com.qmdx00.repository;

import com.qmdx00.entity.Handle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/17 08:47
 * @description 订单处理记录持久化
 */
@Repository
public interface HandleRepository extends JpaRepository<Handle, String> {
    /**
     * 处理订单，修改订单的处理状态为接受或拒绝
     *
     * @param orderId 订单 ID
     * @param status  订单处理状态
     * @return Integer
     */
    @Modifying
    @Query(value = "update t_handle as h set h.admin_id = ?2, h.handle_time = ?3, h.handle_status = ?4 where h.order_id = ?1", nativeQuery = true)
    Integer handleOrder(String orderId, String adminId, Date handleTime, String status);
}
