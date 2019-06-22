package com.qmdx00.repository;

import com.qmdx00.entity.Handle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:47
 * @description 订单处理记录持久化
 */
@Repository
public interface HandleRepository extends JpaRepository<Handle, String> {
}
