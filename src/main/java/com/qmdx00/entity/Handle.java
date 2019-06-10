package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class Handle {
    @Id
    private String handle_id;
    private String admin_id;
    private String record_id;
    private Date handle_time;
    private String handle_result;
}
