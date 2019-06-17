package com.qmdx00.service.impl;

import com.qmdx00.entity.ProductData;
import com.qmdx00.repository.ProductDataRepository;
import com.qmdx00.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductDataServiceImpl implements ProductDataService {

    private final ProductDataRepository productDataRepository;

    @Autowired
    public ProductDataServiceImpl(ProductDataRepository productDataRepository) {
        this.productDataRepository = productDataRepository;
    }

    @Override
    @Transactional
    public ProductData saveProductData(ProductData data) {
        return productDataRepository.save(data);
    }
}
