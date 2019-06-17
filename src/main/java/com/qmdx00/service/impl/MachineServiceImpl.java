package com.qmdx00.service.impl;

import com.qmdx00.entity.Machine;
import com.qmdx00.repository.MachineRepository;
import com.qmdx00.service.MachineService;
import com.qmdx00.util.enums.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    @Transactional
    public ResponseStatus saveMachine(Machine machine) {
        Machine saved = machineRepository.save(machine);
        if (saved != null) {
            return ResponseStatus.UPDATE_SUCCESS;
        } else return ResponseStatus.UPDATE_FAILED;
    }

    @Override
    public List<Machine> findAllMachine() {
        return machineRepository.findAll();
    }

    @Override
    public Machine findMachineById(String id) {
        return machineRepository.findById(id).orElse(null);
    }
}
