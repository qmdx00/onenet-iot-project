package com.qmdx00.repository;

import com.qmdx00.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:55
 * @description 工人信息持久化
 */
@Repository
public interface WorkerRepository extends JpaRepository<Worker, String> {

}
