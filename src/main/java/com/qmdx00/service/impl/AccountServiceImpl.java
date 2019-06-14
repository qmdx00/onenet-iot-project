package com.qmdx00.service.impl;

import com.qmdx00.entity.Account;
import com.qmdx00.repository.AccountRepository;
import com.qmdx00.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findAccountById(String id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findAccountByNameAndPassword(String name, String password) {
        return accountRepository.findByNameAndPassword(name, password).orElse(null);
    }
}
