package com.qmdx00.service.impl;

import com.qmdx00.entity.Customer;
import com.qmdx00.repository.CustomerRepository;
import com.qmdx00.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer findCustomerById(String id) {
        return repository.findByCustomerId(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }
}
