package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.persistence.entity.History;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    
}
