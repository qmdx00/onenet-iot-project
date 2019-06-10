package com.qmdx00.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/10 10:34
 * @description 客户实体
 */
@Entity
@Table(name = "t_customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    // 编号
    @Id
    private String customer_id;
    // 姓名
    private String name;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 注册时间
    private Date create_time;
}
