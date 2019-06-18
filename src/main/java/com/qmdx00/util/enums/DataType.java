package com.qmdx00.util.enums;

/**
 * @author yuanweimin
 * @date 19/06/18 19:22
 * @description 设备状态数据类型和描述
 */
@SuppressWarnings("unused")
public enum DataType {
    /**
     * 数据类型枚举
     */
    TEMPERATURE("temperature", "温度"),
    HUMIDITY("humidity", "湿度"),
    VOLTAGE("voltage", "电压"),
    ELECTRIC("electric", "电流"),
    POWER("power", "功耗"),
    WEIGHT("weight", "加工货物重量"),
    ALARM("alarm", "报警信息"),
    MOTOR_SPEED("electricSpeed", "电机转速"),
    MOTOR_DIR("electricDir", "电机转向"),
    SLIDE_SPEED("slideSpeed", "滑台移动速度"),
    SLIDE_DIR("slideDir", "滑台移动方向"),
    ROD_DISTANCE("rodDistance", "推杆推送距离"),
    BUTTON_STATUS("buttonStatus", "机器设备开关状态");

    private String type;
    private String describe;

    DataType(String type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public String getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }

    @Override
    public String toString() {
        return "DataType{" +
                "type='" + type + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
