package com.qmdx00.service;

import java.util.Map;

/**
 * @author yuanweimin
 * @date 19/07/11 11:18
 * @description 生产任务 Service 逻辑
 */
public interface TaskService {

    /**
     * 通过任务 ID 获取任务各个工序的进度
     *
     * @param taskId 任务 ID
     * @return List
     */
    Map<String, Object> getStatus(String taskId);
}
