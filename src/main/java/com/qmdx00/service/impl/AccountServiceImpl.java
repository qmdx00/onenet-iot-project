package com.qmdx00.service.impl;

import com.qmdx00.entity.Account;
import com.qmdx00.repository.AccountRepository;
import com.qmdx00.service.AccountService;
import com.qmdx00.util.MapUtil;
import com.qmdx00.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final TokenUtil tokenUtil;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TokenUtil tokenUtil) {
        this.accountRepository = accountRepository;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public String accountLogin(String name, String password) {
        Optional<Account> optional = accountRepository.findByNameAndPassword(name, password);
        if (optional.isPresent()) {
            String id = optional.get().getId();
            return tokenUtil.createJwt(MapUtil.create("account_id", id), 1000 * 60 * 60 * 6);
        } else {
            return "";
        }
    }

    @Override
    public Account findAccountById(String id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByName(String name) {
        return accountRepository.findByName(name).orElse(null);
    }
}
