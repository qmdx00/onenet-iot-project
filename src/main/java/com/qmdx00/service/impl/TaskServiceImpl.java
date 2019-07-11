package com.qmdx00.service.impl;

import com.qmdx00.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public Map<String, Object> getStatus(String taskId) {
        // Todo 任务进度逻辑，
        return null;
    }
}
