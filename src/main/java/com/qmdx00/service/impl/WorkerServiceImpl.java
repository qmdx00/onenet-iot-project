package com.qmdx00.service.impl;

import com.qmdx00.entity.Worker;
import com.qmdx00.repository.WorkerRepository;
import com.qmdx00.service.WorkerService;
import com.qmdx00.util.enums.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> getAllWorker() {
        return workerRepository.findAll();
    }

    @Override
    public Worker getWorkerById(String id) {
        return workerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseStatus saveWorker(Worker worker) {
        Worker saved = workerRepository.save(worker);
        if (saved != null) {
            return ResponseStatus.UPDATE_SUCCESS;
        } else {
            return ResponseStatus.UPDATE_FAILED;
        }
    }

    @Override
    @Transactional
    public Integer updateWorker(Worker worker) {
        return workerRepository.updateWorkerById(
                worker.getWorkerId(),
                worker.getName(),
                worker.getPhone(),
                worker.getDepartment(),
                worker.getPost());
    }

    @Override
    @Modifying
    @Transactional
    public Integer deleteWorkerById(String id) {
        return workerRepository.deleteWorkerByWorkerId(id);
    }
}
