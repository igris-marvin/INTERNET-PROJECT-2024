package com.sanienterprise.dawn.persistence.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanienterprise.dawn.persistence.entity.Patron;

@Primary
@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {
    
}
