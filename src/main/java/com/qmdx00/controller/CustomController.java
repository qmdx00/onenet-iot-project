package com.qmdx00.controller;

import com.qmdx00.entity.Customer;
import com.qmdx00.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuanweimin
 * @date 19/06/10 15:38
 * @description 客户接口 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomController extends BaseController {

    private final CustomerService customerService;

    @Autowired
    public CustomController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void createCustomer() {
        customerService.saveCustomer(Customer.builder().build());
    }

    @GetMapping("/{id}")
    public void getCustomerById(@PathVariable String id) {
        log.info("{}", id);
    }

    @PutMapping("/{id}")
    public void updateCustomerById(@PathVariable String id) {

    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable String id) {

    }
}
