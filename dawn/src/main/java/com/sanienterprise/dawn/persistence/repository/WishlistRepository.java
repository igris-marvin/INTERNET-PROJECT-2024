package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.persistence.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    
}
