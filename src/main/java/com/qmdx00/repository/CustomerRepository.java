package com.qmdx00.repository;

import com.qmdx00.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/**
 * @author yuanweimin
 * @date 19/06/10 10:35
 * @description 客户持久化
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    /**
     * 通过顾客 ID 查找顾客
     *
     * @param customer_id 顾客 ID
     * @return Optional<Customer>
     */
    Optional<Customer> findByCustomerId(String customer_id);

    /**
     * 通过客户 ID 修改客户信息
     *
     * @param id         客户 ID
     * @param name       客户姓名
     * @param phone      客户电话
     * @param email      客户邮箱
     * @param updateTime 修改日期
     * @return Integer
     */
    @Modifying
    @Query(value = "update t_customer as c set c.name = ?2, c.phone = ?3, c.email = ?4, c.update_time = ?5 where c.customer_id = ?1", nativeQuery = true)
    Integer updateCustomerById(String id, String name, String phone, String email, Date updateTime);

    /**
     * 通过 ID 删除客户信息
     *
     * @param customer_id 客户 ID
     * @return Integer
     */
    Integer deleteCustomerByCustomerId(String customer_id);
}
