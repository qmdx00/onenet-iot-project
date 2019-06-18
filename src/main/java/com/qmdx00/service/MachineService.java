package com.qmdx00.service;

import com.qmdx00.entity.Machine;
import com.qmdx00.util.enums.ResponseStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/17 10:31
 * @description 机器设备 service
 */
public interface MachineService {
    /**
     * 保存设备信息
     *
     * @param machine 设备信息
     * @return ResponseStatus
     */
    ResponseStatus saveMachine(Machine machine);

    /**
     * 获取机器设备列表
     *
     * @return List
     */
    List<Machine> findAllMachine();

    /**
     * 通过 ID 获取指定设备信息
     *
     * @param id 设备 ID
     * @return Machine
     */
    Machine findMachineById(String id);

    /**
     * 通过ID修改设备信息
     *
     * @param machine 设备信息
     * @return Integer
     */
    Integer updateMachine(Machine machine);
}
