package com.qmdx00.service.impl;

import com.qmdx00.entity.Handle;
import com.qmdx00.repository.HandleRepository;
import com.qmdx00.service.HandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandleServiceImpl implements HandleService {
    private final HandleRepository handleRepository;

    @Autowired
    public HandleServiceImpl(HandleRepository handleRepository) {
        this.handleRepository = handleRepository;
    }

    @Override
    public List<Handle> getAllHandle() {
        return handleRepository.findAll();
    }

    @Override
    public Handle getHandle(String orderId) {
        return handleRepository.findById(orderId).orElse(null);
    }

    @Override
    public Handle insertHandle(Handle handle) {
        return handleRepository.save(handle);
    }
}
