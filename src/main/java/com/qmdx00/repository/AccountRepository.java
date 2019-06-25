package com.qmdx00.repository;

import com.qmdx00.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author yuanweimin
 * @date 19/06/11 08:58
 * @description 账号持久化
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    /**
     * 通过 ID 查找账号
     *
     * @param id 账号ID
     * @return Optional<Account>
     */
    Optional<Account> findById(String id);

    /**
     * 通过用户名查找账户
     *
     * @param name 用户名
     * @return Optional<Account>
     */
    Optional<Account> findByName(String name);

    /**
     * 通过用户名和密码查找账户
     *
     * @param name     用户名
     * @param password 密码
     * @return Optional<Account>
     */
    Optional<Account> findByNameAndPassword(String name, String password);

    /**
     * 通过账号 ID 删除账号
     *
     * @param id 账号 ID
     * @return Integer
     */
    Integer deleteAccountById(String id);
}
