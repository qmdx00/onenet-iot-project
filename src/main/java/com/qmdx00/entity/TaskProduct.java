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
 * @date 19/07/08 21:15
 * @description 生产任务实体
 */
@Entity
@Table(name = "t_task_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskProduct implements Serializable {
    // 任务单编号
    @Id
    private String taskId;
    // 订单编号
    private String orderId;
    // 任务优先级，1 ~ 10，越小越高
    private String priority;
    // 第一道工序任务量
    private String first;
    // 第二道工序任务量
    private String second;
    // 第三道工序任务量
    private String third;
    // 任务创建日期
    private Date creatTime;
    // 任务截止日期
    private Date deadline;
}
