package com.sanienterprise.dawn.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.repository.PatronRepository;

@Service
public class HiddenService {
    
    @Autowired
    private PatronRepository patRepo;

    public String[] getRoles() {
        String[] roles = {"CUSTOMER", "ADMIN", "CUSTOMER_ADMIN"};

        return roles;
    }


}
