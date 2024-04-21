package com.sanienterprise.dawn.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.model.History;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    
}
