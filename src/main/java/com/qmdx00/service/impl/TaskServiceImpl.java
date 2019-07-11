package com.qmdx00.service.impl;

import com.qmdx00.entity.TaskStatus;
import com.qmdx00.repository.TaskStatusRepository;
import com.qmdx00.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    public TaskServiceImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public Map<String, Object> getStatus(String taskId) {
        Map<String, Object> status = new HashMap<>();

        List<TaskStatus> first = taskStatusRepository.findAllByTaskIdAndBelong(taskId, "first");
        List<TaskStatus> second = taskStatusRepository.findAllByTaskIdAndBelong(taskId, "second");
        List<TaskStatus> third = taskStatusRepository.findAllByTaskIdAndBelong(taskId, "third");

        status.put("first", getMax(first));
        status.put("second", getMax(second));
        status.put("third", getMax(third));

        return status;
    }

    /**
     * 获取任务记录中完成最多的一条
     *
     * @param list 任务记录列表
     * @return TaskStatus
     */
    private TaskStatus getMax(List<TaskStatus> list) {
        if (list != null && list.size() > 0) {
            return list.stream()
                    .max(Comparator.comparing(TaskStatus::getDoneNum))
                    .get();
        } else return null;
    }
}
