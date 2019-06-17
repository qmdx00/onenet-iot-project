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
 * @date 19/06/10 16:25
 * @description 管理员实体
 */
@Entity
@Table(name = "t_admin")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
    // 管理员编号
    @Id
    private String adminId;
    // 姓名
    private String name;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 注册时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
}
