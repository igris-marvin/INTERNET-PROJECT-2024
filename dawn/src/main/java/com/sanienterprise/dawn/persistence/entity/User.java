package com.sanienterprise.dawn.persistence.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    
    @Column(nullable = false, length = 13, unique = true)
    private String id_number;

    @Column(nullable = false, length = 20)
    private String name;
    
    @Column(nullable = false, length = 20)
    private String surname;
    
    @Column(nullable = false, length = 50)
    private String email;
    
    @Column(nullable = false, length = 20)
    private String contact_number;

    public User(String id_number, String name, String surname, String email, String contact_number) {
        this.id_number = id_number;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contact_number = contact_number;
    }
}
