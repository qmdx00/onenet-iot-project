package com.qmdx00.repository;

import com.qmdx00.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author yuanweimin
 * @date 19/06/10 10:35
 * @description 客户持久化
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findCustomerByCustomer_id(String id);
}
