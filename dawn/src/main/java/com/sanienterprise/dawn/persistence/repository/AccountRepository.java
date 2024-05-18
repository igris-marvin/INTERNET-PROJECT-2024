package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sanienterprise.dawn.persistence.entity.Account;
import com.sanienterprise.dawn.persistence.entity.Patron;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query
    Account findByUsername(String username);
    
}
