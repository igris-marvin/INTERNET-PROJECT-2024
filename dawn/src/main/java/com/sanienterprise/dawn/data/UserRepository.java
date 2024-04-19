package com.sanienterprise.dawn.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanienterprise.dawn.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
