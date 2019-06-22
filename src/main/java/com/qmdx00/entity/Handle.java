package com.qmdx00.entity;

import com.qmdx00.util.enums.HandleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/10 16:28
 * @description 订单处理记录实体
 */
@Entity
@Table(name = "t_order_handle")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Handle implements Serializable {
    // 处理的订单编号
    @Id
    private String orderId;
    // 处理的管理员的编号
    private String adminId;
    // 处理时间
    private Date handleTime;
    // 处理结果
    @Enumerated(EnumType.STRING)
    private HandleStatus handleStatus;
}
