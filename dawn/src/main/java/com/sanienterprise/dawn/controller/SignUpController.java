package com.sanienterprise.dawn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanienterprise.dawn.data.AdminRepository;
import com.sanienterprise.dawn.data.CustomerRepository;
import com.sanienterprise.dawn.data.UserRepository;
import com.sanienterprise.dawn.model.Admin;
import com.sanienterprise.dawn.model.Customer;
import com.sanienterprise.dawn.model.User;
import com.sanienterprise.dawn.model.Admin.Permission;

@Controller
@RequestMapping("/user")
public class SignUpController {

    @Autowired
    private UserRepository userCustRepo;

    @Autowired
    private UserRepository userAdminRepo;

    public SignUpController(
        CustomerRepository userCustRepo,
        AdminRepository userAdminRepo
    ) {
        this.userCustRepo = userCustRepo;
        this.userAdminRepo = userAdminRepo;
    }

    // GET SIGN UP
    @GetMapping("/signup")
    public String goSignUp() {
        return "signup";
    }

    @ModelAttribute(name = "customer")
    public User getCustomer() {

        Customer cust = new Customer();

        return cust;
    }

    // POST SIGN UP
    @PostMapping
    public String comeSignUp(
        @ModelAttribute(name = "customer") Customer customer
    ) {
        Admin admin = new Admin();

        admin.setId_number(2738947625672L);
        admin.setName("main");
        admin.setSurname("Issue");
        admin.setEmail("admin@sep.com");
        admin.setContact_number("+27 92 287 2839");
        admin.setAdmin_username("admin");
        admin.setAdmin_password("123");
        admin.setPermissions(Permission.READ_WRITE);

        userCustRepo.save(customer);
        userAdminRepo.save(admin);

        return "/";
    }
}
