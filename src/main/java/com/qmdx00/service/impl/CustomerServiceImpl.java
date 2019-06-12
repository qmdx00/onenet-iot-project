package com.qmdx00.service.impl;

import com.qmdx00.entity.Account;
import com.qmdx00.entity.Customer;
import com.qmdx00.repository.AccountRepository;
import com.qmdx00.repository.CustomerRepository;
import com.qmdx00.service.CustomerService;
import com.qmdx00.util.enums.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Customer findCustomerById(String id) {
        return customerRepository.findByCustomerId(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public ResponseStatus saveCustomer(Customer customer, Account account) {
        Customer savedCustomer = customerRepository.save(customer);
        Account savedAccount = accountRepository.save(account);
        if (savedAccount != null && savedCustomer != null) {
            return ResponseStatus.UPDATE_SUCCESS;
        } else {
            return ResponseStatus.UPDATE_FAILED;
        }
    }

    @Override
    @Transactional
    public Integer updateCustomer(String id, String name, String phone, String email) {
        return customerRepository.updateCustomerById(id, name, email, phone, new Date());
    }

    @Override
    public Integer deleteCustomer(String id) {
        return customerRepository.deleteCustomerByCustomerId(id);
    }
}
