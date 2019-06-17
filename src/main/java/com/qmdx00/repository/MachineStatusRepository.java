package com.qmdx00.repository;

import com.qmdx00.entity.MachineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanweimin
 * @date 19/06/17 08:49
 * @description 机器状态数据持久化
 */
@Repository
public interface MachineStatusRepository extends JpaRepository<MachineStatus, String> {

}
