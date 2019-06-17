package com.qmdx00.repository;

import com.qmdx00.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/17 08:46
 * @description 管理员持久化
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    /**
     * 通过 ID 修改管理员信息
     *
     * @param id         管理员 ID
     * @param name       姓名
     * @param phone      电话
     * @param email      邮箱
     * @param updateTime 修改日期
     * @return Integer
     */
    @Modifying
    @Query(value = "update t_admin as a set a.name = ?2, a.phone = ?3, a.email = ?4, a.update_time = ?5 where a.admin_id = ?1", nativeQuery = true)
    Integer updateCustomerById(String id, String name, String phone, String email, Date updateTime);

    /**
     * 通过 ID 删除管理员信息
     *
     * @param admin_id 管理员 ID
     * @return Integer
     */
    Integer deleteAdminByAdminId(String admin_id);
}
