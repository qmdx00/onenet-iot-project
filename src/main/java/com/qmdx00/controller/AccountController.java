package com.qmdx00.controller;

import com.qmdx00.entity.Account;
import com.qmdx00.service.AccountService;
import com.qmdx00.util.ResultUtil;
import com.qmdx00.util.TokenUtil;
import com.qmdx00.util.VerifyUtil;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuanweimin
 * @date 19/06/14 09:03
 * @description 账户控制器
 */
@RestController
@RequestMapping("/api/account")
public class AccountController extends BaseController {
    private final TokenUtil tokenUtil;
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService, TokenUtil tokenUtil) {
        this.accountService = accountService;
        this.tokenUtil = tokenUtil;
    }


//    @GetMapping
//    public Response getLogin(@RequestParam("name") String name,
//                             @RequestParam("password") String password) {
//
//        if (!VerifyUtil.checkString(name, password)) {
//            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
//        } else {
//            Account account = accountService.findAccountByNameAndPassword(name, password);
//            if (account != null) {
//                tokenUtil.createJwt()
//            }
//            return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, account);
//        }
//    }
}
