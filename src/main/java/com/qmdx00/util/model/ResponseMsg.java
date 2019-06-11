package com.qmdx00.util.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuanweimin
 * @date 19/06/11 10:09
 * @description 返回信息，不带数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMsg implements Response {
    private Integer code;
    private String msg;
}
