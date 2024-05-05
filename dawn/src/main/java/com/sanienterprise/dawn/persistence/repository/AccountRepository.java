package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.persistence.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
}
