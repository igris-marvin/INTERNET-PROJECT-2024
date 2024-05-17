package com.sanienterprise.dawn.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.entity.Account;
import com.sanienterprise.dawn.persistence.entity.Admin;
import com.sanienterprise.dawn.persistence.entity.Patron;
import com.sanienterprise.dawn.persistence.repository.AccountRepository;
import com.sanienterprise.dawn.persistence.repository.PatronRepository;

@Service
public class SignUpService {
    
    @Autowired
    private PatronRepository patRepo;
    
    @Autowired
    private AccountRepository accRepo;

    private List<String> getAllCustomerUsernames() {
        List<Account> accs = accRepo.findAll();
        List<Patron> ads = patRepo.findAll();

        List<String> usernames = new ArrayList<>();

        for (Account  x: accs) {
            
            usernames.add(x.getUsername());

        }

        for (Patron y: ads) {
            
            if(y instanceof Admin) {
                Admin adm = (Admin) y;

                usernames.add(adm.getAdmin_username());
            }
        }

        return usernames;
    }

    public String validatePassword(String password, String c_password) {
        if(password.equals(c_password)) {
            return "";
        } else {
            return "Passwords do not match!";
        }
    }

    public String checkUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkUsername'");
    }

    public String checkIdNumber(String id_number) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkIdNumber'");
    }

}
