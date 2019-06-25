package com.qmdx00.service.impl;

import com.qmdx00.entity.productData.FirstData;
import com.qmdx00.entity.productData.FourthData;
import com.qmdx00.entity.productData.SecondData;
import com.qmdx00.entity.productData.ThirdData;
import com.qmdx00.repository.productData.FirstDataRepository;
import com.qmdx00.repository.productData.FourthDataRepository;
import com.qmdx00.repository.productData.SecondDataRepository;
import com.qmdx00.repository.productData.ThirdDataRepository;
import com.qmdx00.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@SuppressWarnings("unchecked")
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
        HashMap map = new HashMap();
        Optional<FourthData> fourthData = fourthDataRepository.findByProductId(productId);
        if (fourthData.isPresent()) {
            FourthData fourth = fourthData.get();
            map.put("fourth", fourth);
            Optional<ThirdData> thirdData = thirdDataRepository.findByProductId(fourth.getPrevious());
            if (thirdData.isPresent()) {
                ThirdData third = thirdData.get();
                map.put("third", third);
                Optional<SecondData> secondData = secondDataRepository.findByProductId(third.getPrevious());
                if (secondData.isPresent()) {
                    SecondData second = secondData.get();
                    map.put("second", second);
                    Optional<FirstData> firstData = firstDataRepository.findByProductId(second.getPrevious());
                    if (firstData.isPresent()) {
                        FirstData first = firstData.get();
                        map.put("first", first);
                    }
                }
            }
        }
        return map;
    }
}
