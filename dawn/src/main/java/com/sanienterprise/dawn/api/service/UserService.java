package com.sanienterprise.dawn.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.entity.Account;
import com.sanienterprise.dawn.persistence.entity.Customer;
import com.sanienterprise.dawn.persistence.entity.Patron;
import com.sanienterprise.dawn.persistence.repository.AccountRepository;
import com.sanienterprise.dawn.persistence.repository.PatronRepository;

@Service
public class UserService {
    
    @Autowired
    private PatronRepository patRepo;

    @Autowired
    private AccountRepository accRepo;

    public Customer getUSerDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        System.out.println(username);

        Account account = accRepo.findByUsername(username);

        Integer acc_id = account.getAccount_id();

        Patron patron = patRepo.findPatronByAccountId(acc_id);

        if(patron instanceof Customer) {
            Customer cust = (Customer) patron;

            System.out.println(cust.toString());
        }

        return null;
    }
}
