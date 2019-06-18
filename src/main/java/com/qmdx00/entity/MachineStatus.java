package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
    // 机器编号
    @Id
    private String machineId;
    // 时间戳
    private Long timeStamp;
    // 温度
    private Double temperature;
    // 湿度
    private Double humidity;
    // 电压
    private Double voltage;
    // 电流
    private Double electric;
    // 功耗
    private Double power;
    // 加工货物重量
    private Double weight;

    // 电机转速
    private Integer electricSpeed;
    // 电机转向
    private Character electricDir;

    // 滑台移动速度
    private Integer slideSpeed;
    // 滑台移动方向
    private Character slideDir;

    // 推杆推送距离
    private Integer rodDistance;

    // 机器设备开关状态
    private Integer buttonStatus;
}
