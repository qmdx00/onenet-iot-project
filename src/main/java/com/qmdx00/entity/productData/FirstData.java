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
 * @description 生产过程第一道工序实体
 */
@Entity
@Table(name = "t_product_first")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstData implements Serializable {
    // 数据编号
    @Id
    private String dataId;
    // 产品编号
    @Column(unique = true)
    private String productId;
    // 工人 ID
    private String workerId;
    // 铜含量
    private String copper;
    // 锡含量
    private String tin;
    // 锌含量
    private String zinc;
    // 上传时间
    private Date createTime;
}
