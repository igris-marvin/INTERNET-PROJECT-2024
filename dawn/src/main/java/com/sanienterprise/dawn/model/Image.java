package com.sanienterprise.dawn.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer image_id;

    @Column(nullable = false, length = 100)
    private String imageName;

    @Column(columnDefinition = "BLOB", nullable = false)
    private byte[] image_source;
}
