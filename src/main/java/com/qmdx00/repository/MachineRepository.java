package com.qmdx00.repository;

import com.qmdx00.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:49
 * @description 机器设备信息持久化
 */
@Repository
public interface MachineRepository extends JpaRepository<Machine, String> {
}
