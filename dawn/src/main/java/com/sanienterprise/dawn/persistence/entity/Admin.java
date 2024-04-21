package com.sanienterprise.dawn.persistence.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Column(nullable = true, length = 20)
    private String admin_username;

    @Column(nullable = true, length = 20)
    private String admin_password;

    @Column(nullable = true, length = 225)
    @Enumerated(value = EnumType.STRING)
    private Permission permissions;
    
    public enum Permission {
        READ_ONLY, READ_WRITE;
    }

    public Admin(String id_number, String name, String surname, String email, String contact_number,
            String admin_username, String admin_password, Permission permissions) {
        super(id_number, name, surname, email, contact_number);
        this.admin_username = admin_username;
        this.admin_password = admin_password;
        this.permissions = permissions;
    }
}