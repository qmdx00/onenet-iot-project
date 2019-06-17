package com.qmdx00.service;

import com.qmdx00.entity.Record;

import java.util.List;

/**
 * @author yuanweimin
 * @date 19/06/17 20:34
 * @description 设备操作记录逻辑
 */
public interface RecordService {

    /**
     * 设备操作记录保持
     *
     * @param record 操作记录
     * @return Record
     */
    Record saveRecord(Record record);

    /**
     * 获取所有操作记录
     *
     * @return List
     */
    List<Record> getAllRecord();

    /**
     * 通过 ID 获取操作记录
     *
     * @param id 记录 ID
     * @return Record
     */
    Record getRecordById(String id);

    /**
     * 通过 ID 删除操作记录
     *
     * @param id 记录 ID
     * @return Integer
     */
    Integer deleteRecordById(String id);
}
