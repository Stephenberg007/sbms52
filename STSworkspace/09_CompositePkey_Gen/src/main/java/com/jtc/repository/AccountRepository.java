package com.jtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtc.entity.Account;
import com.jtc.entity.AccountPK;

public interface AccountRepository extends JpaRepository<Account,AccountPK> {

}
