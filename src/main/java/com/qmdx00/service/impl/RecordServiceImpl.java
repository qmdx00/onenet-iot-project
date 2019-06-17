package com.qmdx00.service.impl;

import com.qmdx00.entity.Record;
import com.qmdx00.repository.RecordRepository;
import com.qmdx00.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    @Transactional
    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public List<Record> getAllRecord() {
        return recordRepository.findAll();
    }

    @Override
    public Record getRecordById(String id) {
        return recordRepository.findById(id).orElse(null);
    }

    @Override
    @Modifying
    @Transactional
    public Integer deleteRecordById(String id) {
        return recordRepository.deleteRecordByRecordId(id);
    }
}
