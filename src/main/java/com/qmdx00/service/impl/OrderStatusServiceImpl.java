package com.qmdx00.service.impl;

import com.qmdx00.entity.OrderStatus;
import com.qmdx00.repository.OrderStatusRepository;
import com.qmdx00.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public OrderStatus getStatusById(String orderId) {
        return orderStatusRepository.findById(orderId).orElse(null);
    }

    @Override
    @Transactional
    public OrderStatus saveStatus(OrderStatus status) {
        return orderStatusRepository.save(status);
    }

    @Override
    @Transactional
    public Integer updateStatus(OrderStatus status) {
        return orderStatusRepository.updateOrderStatus(status.getOrderId(), status.getOrderStatus().name());
    }
}
