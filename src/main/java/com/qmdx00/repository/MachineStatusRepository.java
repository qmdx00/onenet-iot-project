package com.qmdx00.repository;

import com.qmdx00.entity.MachineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineStatusRepository extends JpaRepository<MachineStatus, String> {

}
