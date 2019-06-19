package com.qmdx00.service;

import com.qmdx00.entity.MachineStatus;

import java.util.Date;
import java.util.List;

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

    /**
     * 通过日期查找历史状态数据
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return List
     */
    List<MachineStatus> findStatusByTime(Date start, Date end);

    /**
     * 查找历史时刻到现在的所有状态数据
     *
     * @param last 历史时刻
     * @return List
     */
    List<MachineStatus> findStatusToNow(Date last);
}
