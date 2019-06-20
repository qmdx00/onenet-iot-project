package com.qmdx00.util.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/20 19:43
 * @description 对应一条生产过程数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductData {
    private String workId;
    private String productId;
    private String length;
    private String weight;
    private String diameter;
    private String copper;
    private String tin;
    private String zinc;
    private Date createTime;
}
