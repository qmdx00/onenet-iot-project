package com.qmdx00.service.impl;

import com.qmdx00.repository.ProducibleRepository;
import com.qmdx00.service.ProducibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducibleServiceImpl implements ProducibleService {

    private final ProducibleRepository producibleRepository;

    @Autowired
    public ProducibleServiceImpl(ProducibleRepository producibleRepository) {
        this.producibleRepository = producibleRepository;
    }


}
