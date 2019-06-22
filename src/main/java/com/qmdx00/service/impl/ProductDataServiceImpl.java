package com.qmdx00.service.impl;

import com.qmdx00.repository.productData.FirstDataRepository;
import com.qmdx00.repository.productData.FourthDataRepository;
import com.qmdx00.repository.productData.SecondDataRepository;
import com.qmdx00.repository.productData.ThirdDataRepository;
import com.qmdx00.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProductDataServiceImpl implements ProductDataService {

    private final FirstDataRepository firstDataRepository;
    private final SecondDataRepository secondDataRepository;
    private final ThirdDataRepository thirdDataRepository;
    private final FourthDataRepository fourthDataRepository;

    @Autowired
    public ProductDataServiceImpl(FirstDataRepository firstDataRepository, SecondDataRepository secondDataRepository, ThirdDataRepository thirdDataRepository, FourthDataRepository fourthDataRepository) {
        this.firstDataRepository = firstDataRepository;
        this.secondDataRepository = secondDataRepository;
        this.thirdDataRepository = thirdDataRepository;
        this.fourthDataRepository = fourthDataRepository;
    }

    @Override
    public HashMap getData(String productId) {
        // Todo 生产过程数据查询逻辑
        return null;
    }
}
