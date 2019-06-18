package com.qmdx00.repository;

import com.qmdx00.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:49
 * @description 机器设备信息持久化
 */
@Repository
public interface MachineRepository extends JpaRepository<Machine, String> {
    /**
     * 通过 ID 修改设备信息
     *
     * @param id   设备 ID
     * @param name 设备名称
     * @param type 类型
     * @param desc 描述
     * @return Integer
     */
    @Modifying
    @Query(value = "update t_machine as m set m.name = ?2, m.type = ?3, m.machine_desc = ?4 where m.machine_id = ?1", nativeQuery = true)
    Integer updateMachineById(String id, String name, String type, String desc);
}
