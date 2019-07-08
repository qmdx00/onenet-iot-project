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
 * @date 19/07/08 21:15
 * @description 生产任务实体
 */
@Entity
@Table(name = "t_product_task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTask implements Serializable {
    @Id
    private String taskId;
}
