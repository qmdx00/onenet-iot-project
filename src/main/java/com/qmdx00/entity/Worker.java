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
 * @date 19/06/10 11:11
 * @description 工人实体类
 */
@Entity
@Table(name = "t_worker")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Worker implements Serializable {
    // 工人编号
    @Id
    private String worker_id;
    // 姓名
    private String name;
    // 手机号
    private String phone;
    // 所属部门
    private String department;
    // 职位
    private String post;
}
