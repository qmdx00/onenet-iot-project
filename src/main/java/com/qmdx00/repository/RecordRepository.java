package com.qmdx00.repository;

import com.qmdx00.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:54
 * @description 设备操作记录持久化
 */
@Repository
public interface RecordRepository extends JpaRepository<Record, String> {
}
