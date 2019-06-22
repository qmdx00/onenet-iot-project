package com.qmdx00.entity;

import com.qmdx00.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yuanweimin
 * @date 19/06/10 16:28
 * @description 订单状态表
 */
@Entity
@Table(name = "t_order_status")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus implements Serializable {
    // 处理的订单编号
    @Id
    private String orderId;
    // 订单状态
    @Enumerated(EnumType.STRING)
    private Status orderStatus;
}
