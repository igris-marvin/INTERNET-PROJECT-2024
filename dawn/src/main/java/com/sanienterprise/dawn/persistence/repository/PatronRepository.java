package com.sanienterprise.dawn.persistence.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanienterprise.dawn.persistence.entity.Patron;

@Primary
@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {

    @Transactional
    @Query("SELECT COUNT(*) FROM Patron i WHERE i.role=:role")
    public int countPatronByRole(
        @Param("role") String role
    );

    @Transactional
    @Query("SELECT i FROM Patron i WHERE i.admin_username = :username")
    public Patron findPatronByUsername (
        @Param("username") String username
    );
}
