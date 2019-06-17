package com.qmdx00.service.impl;

import com.qmdx00.entity.Producible;
import com.qmdx00.repository.ProducibleRepository;
import com.qmdx00.service.ProducibleService;
import com.qmdx00.util.enums.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProducibleServiceImpl implements ProducibleService {

    private final ProducibleRepository producibleRepository;

    @Autowired
    public ProducibleServiceImpl(ProducibleRepository producibleRepository) {
        this.producibleRepository = producibleRepository;
    }

    @Override
    public List<Producible> findAllProducible() {
        return producibleRepository.findAll();
    }

    @Override
    public Producible findProducibleById(String id) {
        return producibleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseStatus saveProducible(Producible producible) {
        Producible saved = producibleRepository.save(producible);
        if (saved != null) {
            return ResponseStatus.UPDATE_SUCCESS;
        } else {
            return ResponseStatus.UPDATE_FAILED;
        }
    }

    @Override
    @Modifying
    @Transactional
    public Integer deleteProducibleById(String id) {
        return producibleRepository.deleteProducibleByProducibleId(id);
    }
}
