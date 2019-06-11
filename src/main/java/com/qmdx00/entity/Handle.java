package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    // 处理记录编号
    @Id
    private String handleId;
    // 处理的管理员的编号
    private String adminId;
    // 处理的订单编号
    private String orderId;
    // 处理时间
    private Date handleTime;
    // 处理结果
    private String handleResult;
}
