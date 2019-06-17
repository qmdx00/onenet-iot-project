package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yuanweimin
 * @date 19/06/17 16:47
 * @description 可生产的产品实体
 */
@Entity
@Table(name = "t_producible")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producible implements Serializable {
    // 可生产的产品ID
    @Id
    private String producibleId;
    // 产品名称
    private String name;
    // 产品描述
    private String producibleDesc;
    // 产品类型
    private String type;
    // 产品图片地址
    private String image;
}
