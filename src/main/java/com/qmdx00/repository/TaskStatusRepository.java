package com.qmdx00.repository;

import com.qmdx00.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanweimin
 * @date 19/07/09 09:52
 * @description 生产任务状态记录持久化
 */
@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, String> {
    /**
     * 通过生产任务 ID 获取生产进度信息
     *
     * @param taskId 生产任务 ID
     * @return List
     */
    List<TaskStatus> findAllByTaskId(String taskId);
}
