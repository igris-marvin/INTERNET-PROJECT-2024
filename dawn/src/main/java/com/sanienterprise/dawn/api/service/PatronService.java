package com.sanienterprise.dawn.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.entity.Account;
import com.sanienterprise.dawn.persistence.entity.Admin;
import com.sanienterprise.dawn.persistence.entity.Customer;
import com.sanienterprise.dawn.persistence.entity.Patron;
import com.sanienterprise.dawn.persistence.repository.PatronRepository;

@Service
public class PatronService implements UserDetailsService {
    
    @Autowired
    private PatronRepository patRepo;

    public PatronService(PatronRepository patRepo) {
        this.patRepo = patRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //get details of all users from database
        List<Patron> patrons = patRepo.findAll();

        String get_username = "";
        String get_password = "";
        String[] roles = {"CUSTOMER"};

        System.out.println("fetching: " + username);

        for(Patron x: patrons) {
            System.out.println("PATRON_ID: " + x.getUser_id());

            if(x instanceof Customer) {

                Customer cust = (Customer) x;

                System.out.println("CUSTOMER_ID: " + cust.getUser_id());

                if(patRepo.existsById(cust.getUser_id())) {

                    get_username = cust.getAccount().getUsername();
                    get_password = cust.getAccount().getPassword();
                    roles = cust.getRole().split("#");

                }
            } else {
                Admin admin = (Admin) x;

                System.out.println("CUSTOMER_ID: " + admin.getUser_id());
                
                if(patRepo.existsById(admin.getUser_id())) {

                    get_username = admin.getAdmin_username();
                    get_password = admin.getAdmin_password();
                    roles = admin.getRole().split("#");

                }
            }

            System.out.println("stored: " + get_username);
            System.out.println("fetching: " + username);

            if(get_username.equals(username)) {
                UserDetails customer = User.builder()
                    .username(get_username)
                    .password(get_password)
                    .roles(roles)
                    .build();

                return customer;
            }
        }

        return null;
    }

    public void addUser(String username, String password) {

        Account account = new Account(
            username, 
            password, 
            null
        );

        Patron patron = new Customer(null, null, null, null, null, "ADMIN#CUSTOMER", 0, 'M', null, null, null, account);

        patRepo.save(patron);
    }
}
