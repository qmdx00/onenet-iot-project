package com.qmdx00.repository;

import com.qmdx00.entity.TaskProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/07/09 09:51
 * @description 生产任务计划持久化
 */
@Repository
public interface TaskProductRepository extends JpaRepository<TaskProduct, String> {

}
