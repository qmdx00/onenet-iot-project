package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

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
public class ProductData {
}
