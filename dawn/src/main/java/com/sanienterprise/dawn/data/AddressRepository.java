package com.sanienterprise.dawn.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
