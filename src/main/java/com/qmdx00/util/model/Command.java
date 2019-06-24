package com.qmdx00.util.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuanweimin
 * @date 19/06/24 10:42
 * @description 下发指令模板
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Command {
    // 资源 ID
    private Integer res_id;
    // 对应下发的值
    private String val;
}
