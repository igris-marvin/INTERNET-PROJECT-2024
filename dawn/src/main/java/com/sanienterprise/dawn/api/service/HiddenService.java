package com.sanienterprise.dawn.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.entity.Patron;
import com.sanienterprise.dawn.persistence.entity.Patron.Role;
import com.sanienterprise.dawn.persistence.repository.AdminRepository;
import com.sanienterprise.dawn.persistence.repository.PatronRepository;

@Service
public class HiddenService {
    
    @Autowired
    private PatronRepository patRepo;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();

        Role[] roleArray = Patron.Role.values();

        for (int i = 0; i < roleArray.length; i++) {
            roles.add(roleArray[i].name());
        }

        return roles;
    }


}
