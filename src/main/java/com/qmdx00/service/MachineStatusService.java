package com.qmdx00.service;

import com.qmdx00.entity.MachineStatus;

/**
 * @author yuanweimin
 * @date 19/06/18 21:26
 * @description 机器实时状态数据逻辑
 */
public interface MachineStatusService {
    /**
     * 保存状态数据
     *
     * @param status 状态数据
     * @return MachineStatus
     */
    MachineStatus saveStatus(MachineStatus status);
}
