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
 * @date 19/06/10 16:15
 * @description 设备操作记录实体
 */
@Entity
@Table(name = "t_record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record implements Serializable {
    // 操作记录编号
    @Id
    private String recordId;
    // 操作的机器编号
    private String machineId;
    // 操作员工的编号
    private String workerId;
    // 开始时间
    private Date startTime;
    // 结束时间
    private Date endTime;
}
