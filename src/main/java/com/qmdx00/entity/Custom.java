package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yuanweimin
 * @date 19/06/10 10:34
 * @description 客户实体
 */
@Entity
@Table(name = "t_custom")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Custom {
    @Id
    private String custom_id;
}
