package com.qmdx00.util.enums;

/**
 * @author yuanweimin
 * @date 19/06/11 10:56
 * @description 下发命令枚举
 */
@SuppressWarnings("ALL")
public enum Cmd {
    /**
     * 电机相关
     */
    MOTOR_STOP("11", "电机静止"),
    MOTOR_FORWARD("12", "电机正转"),
    MOTOR_REVERSE("13", "电机反转"),
    MOTOR_FAST("21", "电机速度: 快"),
    MOTOR_MIDDLE("22", "电机速度: 中"),
    MOTOR_SLOW("23", "电机速度: 慢"),
    /**
     * 滑台相关
     */
    SLIDE_OPEN("31", "滑台打开"),
    SLIDE_CLOSE("32", "滑台关闭"),
    SLIDE_FAST("41", "滑台速度: 快"),
    SLIDE_MIDDLE("42", "滑台速度: 中"),
    SLIDE_SLOW("43", "滑台速度: 慢"),
    /**
     * 推杆相关
     */
    ROD_DIS_0("51", "推杆距离: 0cm"),
    ROD_DIS_5("52", "推杆距离: 5cm"),
    ROD_DIS_15("53", "推杆距离: 15cm"),
    ROD_DIS_20("54", "推杆距离: 20cm"),
    /**
     * 风扇相关
     */
    FAN_OPEN("61", "风扇打开"),
    FAN_CLOSE("62", "风扇关闭");

    private String code;
    private String msg;

    Cmd(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Cmd{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }}
