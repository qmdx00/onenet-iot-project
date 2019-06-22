package com.qmdx00.service.impl;

import com.qmdx00.entity.Handle;
import com.qmdx00.entity.Order;
import com.qmdx00.repository.HandleRepository;
import com.qmdx00.repository.OrderRepository;
import com.qmdx00.service.HandleService;
import com.qmdx00.util.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HandleServiceImpl implements HandleService {

    private final OrderRepository orderRepository;
    private final HandleRepository handleRepository;

    @Autowired
    public HandleServiceImpl(HandleRepository handleRepository, OrderRepository orderRepository) {
        this.handleRepository = handleRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Handle> getAllHandle() {
        return handleRepository.findAll();
    }

    @Override
    @Transactional
    public Integer updateHandle(Handle handle) {
        // 查找订单
        Optional<Order> optionalOrder = orderRepository.findById(handle.getOrderId());
        if (optionalOrder.isPresent()) {
            // 修改订单的状态为已处理
            Integer o = orderRepository.handleOrder(handle.getOrderId(), OrderStatus.PROCESSED.name());
            // 修改订单的处理状态
            Integer h = handleRepository.handleOrder(handle.getOrderId(), handle.getAdminId(), handle.getHandleTime(), handle.getHandleStatus().name());
            return o + h;
        } else return 0;
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
