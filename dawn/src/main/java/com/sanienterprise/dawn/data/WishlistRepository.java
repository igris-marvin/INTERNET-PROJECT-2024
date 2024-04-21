package com.sanienterprise.dawn.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    
}
