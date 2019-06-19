package com.qmdx00.entity;

import com.qmdx00.util.enums.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/10 16:26
 * @description 机器设备状态实体
 */
@Entity
@Table(name = "t_machine_status")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineStatus implements Serializable {
    @Id
    // 数据编号
    private String statusId;
    // 机器编号
    private String machineId;
    // 数据生成日期
    private Date createDate;
    // 数据类型
    @Enumerated(EnumType.STRING)
    private DataType type;
    // 数据内容
    private String data;
}
