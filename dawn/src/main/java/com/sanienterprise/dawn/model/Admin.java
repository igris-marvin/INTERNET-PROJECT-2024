package com.sanienterprise.dawn.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(value = "ADMIN")
public class Admin extends User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(nullable = false, length = 20)
    private String admin_username;

    @Column(nullable = false, length = 1000)
    private String admin_password;

    @Column(nullable = false, length = 225)
    @Enumerated(value = EnumType.STRING)
    private Permission permissions;

    public enum Permission {
        READ_ONLY, READ_WRITE;
    }
}