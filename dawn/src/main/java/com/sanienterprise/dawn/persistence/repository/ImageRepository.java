package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.persistence.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    
}
