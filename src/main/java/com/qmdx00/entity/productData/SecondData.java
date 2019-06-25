package com.qmdx00.entity.productData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/20 17:14
 * @description 生产过程第二道工序实体
 */
@Entity
@Table(name = "t_product_second")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondData implements Serializable {
    // 数据编号
    @Id
    private String dataId;
    // 产品编号
    @Column(unique = true)
    private String productId;
    // 工人 ID
    private String workerId;
    // 产品直径 (mm)
    private String diameter;
    // 产品长度 (m)
    private String length;
    // 产品重量 (kg)
    private String weight;
    // 上传时间
    private Date createTime;
    // 前一道工序的数据编号
    private String previous;
}
