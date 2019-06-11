package com.qmdx00.util;

import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.model.Response;
import com.qmdx00.util.model.ResponseData;
import com.qmdx00.util.model.ResponseMsg;

public class ResultUtil {

    /**
     * 返回响应消息
     *
     * @param status 响应状态
     * @param msg    响应信息
     * @return Response
     */
    public static Response returnStatus(ResponseStatus status, String msg) {
        return ResponseMsg.builder()
                .code(status.getCode())
                .msg(msg)
                .build();
    }

    public static Response returnStatus(ResponseStatus status) {
        return returnStatus(status, status.getMsg());
    }

    /**
     * 返回消息和数据
     *
     * @param status 响应状态
     * @param msg    响应消息
     * @param data   响应数据
     * @return Response
     */
    public static Response returnStatusAndData(ResponseStatus status, String msg, Object data) {
        return ResponseData.builder()
                .code(status.getCode())
                .msg(status.getMsg())
                .data(data)
                .build();
    }

    public static Response returnStatusAndData(ResponseStatus status, Object data) {
        return returnStatusAndData(status, status.getMsg(), data);
    }
}
