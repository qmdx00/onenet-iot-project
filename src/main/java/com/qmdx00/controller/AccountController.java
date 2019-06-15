package com.qmdx00.controller;

import com.qmdx00.service.AccountService;
import com.qmdx00.util.MapUtil;
import com.qmdx00.util.ResultUtil;
import com.qmdx00.util.VerifyUtil;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 账户登录授权
     *
     * @param name     账户名
     * @param password 密码
     * @return Response
     */
    @GetMapping
    public Response getLogin(@RequestParam("name") String name,
                             @RequestParam("password") String password) {

        if (!VerifyUtil.checkString(name, password)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            String token = accountService.accountLogin(name, password);
            if (token == null || token.equals("")) {
                return ResultUtil.returnStatus(ResponseStatus.NOT_LOGIN, "账号或密码错误");
            } else {
                return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, MapUtil.create("token", token));
            }
        }
    }
}
