package com.qmdx00.service.impl;

import com.qmdx00.entity.MachineStatus;
import com.qmdx00.repository.MachineStatusRepository;
import com.qmdx00.service.MachineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MachineStatusServiceImpl implements MachineStatusService {

    private final MachineStatusRepository machineStatusRepository;

    @Autowired
    public MachineStatusServiceImpl(MachineStatusRepository machineStatusRepository) {
        this.machineStatusRepository = machineStatusRepository;
    }

    @Override
    @Transactional
    public MachineStatus saveStatus(MachineStatus status) {
        return machineStatusRepository.save(status);
    }
}
