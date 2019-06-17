package com.qmdx00.service.impl;

import com.qmdx00.entity.Account;
import com.qmdx00.entity.Admin;
import com.qmdx00.repository.AccountRepository;
import com.qmdx00.repository.AdminRepository;
import com.qmdx00.service.AdminService;
import com.qmdx00.util.enums.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, AccountRepository accountRepository) {
        this.adminRepository = adminRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Admin findAdminById(String id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseStatus saveAdmin(Admin admin, Account account) {
        Admin savedAdmin = adminRepository.save(admin);
        Account savedAccount = accountRepository.save(account);
        if (savedAccount != null && savedAdmin != null) {
            return ResponseStatus.UPDATE_SUCCESS;
        } else {
            return ResponseStatus.UPDATE_FAILED;
        }
    }

    @Override
    @Transactional
    public Integer updateAdmin(String id, String name, String phone, String email) {
        return adminRepository.updateCustomerById(id, name, phone, email, new Date());
    }

    @Override
    @Modifying
    @Transactional
    public Integer deleteAdmin(String id) {
        Integer acc = accountRepository.deleteAccountById(id);
        Integer ad = adminRepository.deleteAdminByAdminId(id);
        return acc + ad;
    }
}
