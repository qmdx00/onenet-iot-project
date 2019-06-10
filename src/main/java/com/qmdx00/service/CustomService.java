package com.qmdx00.service;

import com.qmdx00.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanweimin
 * @date 19/06/10 12:18
 * @description Custom 逻辑处理类
 */
@Service
public class CustomService {

    private final CustomRepository customRepository;

    @Autowired
    public CustomService(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }


}
