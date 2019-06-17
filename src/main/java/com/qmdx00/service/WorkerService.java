package com.qmdx00.service;

import com.qmdx00.entity.Worker;
import com.qmdx00.util.enums.ResponseStatus;

import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/17 21:25
 * @description 工人信息逻辑
 */
public interface WorkerService {

    /**
     * 获取所有工人信息列表
     *
     * @return
     */
    List<Worker> getAllWorker();

    /**
     * 通过 ID 获取工人信息
     *
     * @param id 工人 ID
     * @return Worker
     */
    Worker getWorkerById(String id);

    /**
     * 保存工人信息
     *
     * @param worker 工人信息
     * @return ResponseStatus
     */
    ResponseStatus saveWorker(Worker worker);

    /**
     * 修改工人信息
     *
     * @param worker 工人信息
     * @return Integer
     */
    Integer updateWorker(Worker worker);

    /**
     * 通过 ID 删除工人信息
     *
     * @param id 工人 ID
     * @return Integer
     */
    Integer deleteWorkerById(String id);
}
