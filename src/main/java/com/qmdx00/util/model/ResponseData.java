package com.qmdx00.util.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuanweimin
 * @date 19/06/11 09:26
 * @description 返回带数据的信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> implements Response {
    private Integer code;
    private String msg;
    private T data;
}
