package com.sanienterprise.dawn.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
}
