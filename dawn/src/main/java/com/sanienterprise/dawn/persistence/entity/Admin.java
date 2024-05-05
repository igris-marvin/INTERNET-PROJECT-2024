package com.sanienterprise.dawn.persistence.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
public class Admin extends Patron {

    @Column(nullable = true, length = 20, unique = true)
    private String admin_username;

    @Column(nullable = true, length = 255)
    private String admin_password;

    public Admin(
        String id_number, 
        String name, 
        String surname, 
        String email, 
        String contact_number, 
        String role,
            String admin_username, 
            String admin_password
    ) {
        super(id_number, name, surname, email, contact_number, role);
        this.admin_username = admin_username;
        this.admin_password = admin_password;
    }
}