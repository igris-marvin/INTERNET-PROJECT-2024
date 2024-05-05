package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.persistence.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
