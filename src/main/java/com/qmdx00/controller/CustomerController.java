package com.qmdx00.controller;

import com.qmdx00.entity.Customer;
import com.qmdx00.service.CustomerService;
import com.qmdx00.util.ResultUtil;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author yuanweimin
 * @date 19/06/10 15:38
 * @description 客户接口 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Response createCustomer(@RequestParam("id") String id,
                                   @RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone) {

        Customer saved = customerService.saveCustomer(Customer.builder()
                .customerId(id)
                .name(name)
                .email(email)
                .phone(phone)
                .createTime(new Date())
                .build());
        log.info("saved customer: {}", saved);
        return ResultUtil.returnStatus(ResponseStatus.UPDATE_SUCCESS);
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
