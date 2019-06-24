package com.qmdx00.service.impl;

import com.qmdx00.entity.Order;
import com.qmdx00.repository.OrderRepository;
import com.qmdx00.service.OrderService;
import com.qmdx00.util.enums.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOrderById(String id, String customerId) {
        return orderRepository.findByOrderIdAndCustomerId(id, customerId).orElse(null);
    }

    @Override
    public List<Order> findAllOrderByCustomer(String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    @Override
    public List<Order> findAllOrderByAdmin() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public ResponseStatus saveOrder(Order order) {
        Order saved = orderRepository.save(order);
        if (saved != null) {
            return ResponseStatus.UPDATE_SUCCESS;
        } else {
            return ResponseStatus.UPDATE_FAILED;
        }
    }

    @Override
    @Transactional
    public Integer updateOrder(Order order) {
        return orderRepository.updateOrderById(
                order.getOrderId(),
                order.getProducibleId(),
                order.getNumber(),
                order.getDiameter(),
                order.getLength(),
                order.getWeight(),
                new Date());
    }
}
