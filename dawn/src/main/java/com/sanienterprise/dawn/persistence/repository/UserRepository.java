package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanienterprise.dawn.persistence.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
