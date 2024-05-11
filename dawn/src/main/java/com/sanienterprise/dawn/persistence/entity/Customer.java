package com.sanienterprise.dawn.persistence.entity;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue(value = "CUSTOMER")
public class Customer extends Patron {

    @Column(nullable = true, length = 20)
    private Integer age;

    @Column(nullable = true, length = 20)
    private char gender;

    @Column(nullable = true, length = 20)
    private Date birth_date;

    @Column(nullable = true, length = 20)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date_added;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_account_id")
    private Account account;

    public Customer(
        String id_number, 
        String name, 
        String surname, 
        String email, 
        String contact_number, 
        String role,
            Integer age, 
            char gender, 
            Date birth_date, 
            Date date_added, 
            Address address, 
            Account account
    ) {
        super(id_number, name, surname, email, contact_number, role);
        this.age = age;
        this.gender = gender;
        this.birth_date = birth_date;
        this.date_added = date_added;
        this.address = address;
        this.account = account;
    }
}