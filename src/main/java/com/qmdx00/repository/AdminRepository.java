package com.qmdx00.repository;

import com.qmdx00.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:46
 * @description 管理员持久化
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
