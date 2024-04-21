package com.sanienterprise.dawn.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    
}
