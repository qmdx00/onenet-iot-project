package com.qmdx00.util.enums;

@SuppressWarnings("ALL")
public enum ResponseStatus {
    /**
     * 状态枚举
     */
    SUCCESS(200, "请求成功"),
    UPDATE_SUCCESS(201, "创建，修改成功"),
    DELETE_SUCCESS(204, "删除成功"),
    PARAMS_ERROR(400, "参数错误"),
    NOT_LOGIN(401, "未登录"),
    VISITED_FORBID(403, "禁止访问"),
    NOT_FOUND(404, "资源未找到"),
    STYTEM_ERROR(500, "系统错误");

    private Integer code;
    private String msg;

    ResponseStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
