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
    // 状态数据编号
    @Id
    private String statusId;
    // 机器编号
    private String machineId;
    // 数据生成日期
    private Date createTime;
    // 温度
    private String temperature;
    // 湿度
    private String humidity;
    // 电压
    private String voltage;
    // 电流
    private String electric;
    // 功耗
    private String power;
    // 加工货物重量
    private String weight;
    // 电机转速
    private String electricSpeed;
    // 电机转向
    private String electricDir;
    // 滑台移动速度
    private String slideSpeed;
    // 滑台移动方向
    private String slideDir;
    // 推杆推送距离
    private String rodDistance;
    // 机器设备开关状态
    private String buttonStatus;
}
