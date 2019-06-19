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
    // 温度超过阈值预警
    private String temperatureWarn;
    // 散热风扇开启状态
    private String fan;
    // 湿度
    private String humidity;
    // 湿度超过阈值预警
    private String humidityWarn;
    // 电压
    private String voltage;
    // 电流
    private String electric;
    // 功耗
    private String power;
    // 加工货物重量
    private String weight;
    // 重量超载预警
    private String weightWarn;
    // 电机开关状态
    private String motorOpen;
    // 电机转速
    private String motorSpeed;
    // 电机转向
    private String motorDir;
    // 滑台开关状态
    private String slideOpen;
    // 滑台移动速度
    private String slideSpeed;
    // 滑台移动方向
    private String slideDir;
    // 推杆推送距离
    private String rodDistance;
    // 机器故障预警
    private String machineError;
    // 预留状态A
    private String reservedA;
    // 预留状态B
    private String reservedB;
    // 预留状态C
    private String reservedC;
    // 预留状态D
    private String reservedD;
}
