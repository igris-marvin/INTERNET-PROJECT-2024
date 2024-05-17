package com.sanienterprise.dawn.api.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.SecurityConfiguration;
import com.sanienterprise.dawn.api.dto.SignUpDTO;
import com.sanienterprise.dawn.persistence.entity.Account;
import com.sanienterprise.dawn.persistence.entity.Admin;
import com.sanienterprise.dawn.persistence.entity.Cart;
import com.sanienterprise.dawn.persistence.entity.Customer;
import com.sanienterprise.dawn.persistence.entity.Patron;
import com.sanienterprise.dawn.persistence.entity.Product;
import com.sanienterprise.dawn.persistence.repository.AccountRepository;
import com.sanienterprise.dawn.persistence.repository.PatronRepository;

@Service
public class SignUpService {
    
    @Autowired
    private PatronRepository patRepo;

    @Autowired
    private SecurityConfiguration security;

    public void createCustomer(SignUpDTO dto, MultipartFile file) {
        String name = dto.getName();
        String surname = dto.getSurname();
        String id_number = dto.getId_number();
        String email = dto.getEmail();
        String contact = dto.getContact();
        String username = dto.getUsername();
        String password = encryptPassword(dto.getPassword());

        Date dob = genDOB(id_number);

        char gender = genGender(id_number);

        Integer age = calculateAge(dob);

        byte[] image = null;

        try {
            image = file.getBytes();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Date date_added = Date.from(Instant.now());

        Patron patron = new Customer(
            id_number, 
            name, 
            surname, 
            email, 
            contact, 
            "CUSTOMER", 
            age, 
            gender, 
            dob, 
            date_added, 
            new Account(
                username, 
                password, 
                image,
                new Cart()
            )
        );

        patRepo.save(patron);
    }

    private String encryptPassword(String password) {
        String encoded = security.passwordEncoder().encode(password);

        return encoded;
    }

    // Extract DOB (first 6 digits)
    private Date genDOB(String idNumber) {
        String dobString = idNumber.substring(0, 6);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        
        Date dateOfBirth = null;;
        try {
            dateOfBirth = dateFormat.parse(dobString);

        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }

        return dateOfBirth;
    }
        
    // Determine gender (7th digit)
    private char genGender(String idNumber) {
        char genderIndicator = idNumber.charAt(6);
        String gender = (Character.getNumericValue(genderIndicator) >= 5) ? "M" : "F";

        return gender.charAt(0);
    }

    private int calculateAge(Date dateOfBirth) {
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        
        return age;
    }

}