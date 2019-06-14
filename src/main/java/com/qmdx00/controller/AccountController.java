package com.qmdx00.controller;

import com.qmdx00.service.AccountService;
import com.qmdx00.util.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanweimin
 * @date 19/06/14 09:03
 * @description 账户控制器
 */
@RestController
@RequestMapping("/api/account")
public class AccountController extends BaseController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

}
