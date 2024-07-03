package com.sanienterprise.dawn.api.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.api.dto.AccountDTO;
import com.sanienterprise.dawn.persistence.entity.Account;
import com.sanienterprise.dawn.persistence.entity.Cart;
import com.sanienterprise.dawn.persistence.entity.Customer;
import com.sanienterprise.dawn.persistence.entity.Patron;
import com.sanienterprise.dawn.persistence.entity.Product;
import com.sanienterprise.dawn.persistence.repository.AccountRepository;
import com.sanienterprise.dawn.persistence.repository.PatronRepository;
import com.sanienterprise.dawn.persistence.repository.ProductRepository;

@Service
public class UserService {
    
    @Autowired
    private PatronRepository patRepo;

    @Autowired
    private AccountRepository accRepo;

    @Autowired
    private ProductRepository proRepo;

    public AccountDTO getUSerDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        System.out.println(username);

        Account account = accRepo.findByUsername(username);

        // Integer acc_id = account.getAccount_id();
        List<Patron> patrons = patRepo.findAll();

        for (Patron x : patrons) {

            if(x instanceof Customer) {
                Customer c = (Customer) x;

                if(c.getAccount().getUsername().equals(account.getUsername())) {
                    
                    AccountDTO acc = new AccountDTO(username, c.getName(), c.getSurname(), c.getEmail(), c.getContact_number(), "data:image/png;base64," + Base64.getEncoder().encodeToString(account.getProfile_image()));

                    return acc;
                }
            }
        }

        return null;
    }

    public void addToCart(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        System.out.println(username);

        Account account = accRepo.findByUsername(username);
        Product product = proRepo.findById(id).get();

        Cart cart = account.getCart();

        List<Product> list = cart.getProducts();

        list.add(product);

        cart.setProducts(list);

        account.setCart(cart);

        account.setCart(cart);

        accRepo.save(account);

    }
}
