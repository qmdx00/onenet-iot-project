package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/10 16:10
 * @description 产品实体
 */
@Entity
@Table(name = "t_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String product_id;
    private String type;
    private Date create_time;
    private String worker_id;
}
