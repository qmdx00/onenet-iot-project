package com.qmdx00.repository;

import com.qmdx00.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author yuanweimin
 * @date 19/06/17 08:51
 * @description 订单持久化
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    /**
     * 通过客户 ID 查找所有订单
     *
     * @param customerId 客户 ID
     * @return List
     */
    List<Order> findAllByCustomerId(String customerId);

    /**
     * 通过订单 ID 和用户 ID 获取订单信息
     *
     * @param orderId    订单 ID
     * @param customerId 客户 ID
     * @return Optional
     */
    Optional<Order> findByOrderIdAndCustomerId(String orderId, String customerId);

    /**
     * 通过 ID 修改订单内容
     *
     * @param id           订单 ID
     * @param producibleId 可生产产品 ID
     * @param number       产品数量
     * @param diameter     产品直径
     * @param length       产品长度
     * @param weight       产品重量
     * @return Integer
     */
    @Modifying
    @Query(value = "update t_order as o set o.producible_id = ?2, o.number = ?3, o.diameter = ?4, o.length = ?5, o.weight = ?6, o.update_time = ?7 where o.order_id = ?1",
            nativeQuery = true)
    Integer updateOrderById(String id, String producibleId, String number, String diameter, String length, String weight, Date updateTime);

    /**
     * 处理订单，修改订单状态为已处理
     *
     * @param orderId 订单 ID
     * @param status  订单状态
     * @return Integer
     */
    @Modifying
    @Query(value = "update t_order as o set o.order_status = ?2 where o.order_id = ?1", nativeQuery = true)
    Integer handleOrder(String orderId, String status);
}
