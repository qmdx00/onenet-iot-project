package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yuanweimin
 * @date 19/06/10 16:25
 * @description 生产过程数据实体
 */
@Entity
@Table(name = "t_product_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductData implements Serializable {
    // 数据编号
    @Id
    private String dataId;
    // 产品编号
    private String productId;
    // 工人 ID
    private String workerId;
    // 产品直径 (mm)
    private String diameter;
    // 产品长度 (m)
    private String length;
    // 产品重量 (kg)
    private String weight;
    // 铜含量
    private String copper;
    // 锡含量
    private String tin;
    // 锌含量
    private String zinc;
}
