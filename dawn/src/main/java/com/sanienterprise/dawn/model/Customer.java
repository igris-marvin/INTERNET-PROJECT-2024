package com.sanienterprise.dawn.model;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Customer extends User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(nullable = false, length = 20)
    private Integer age;

    @Column(nullable = false, length = 20)
    private char gender;

    @Column(nullable = false, length = 20)
    private Date birth_date;

    @Column(nullable = false, length = 20)
    private Date date_added;

    /*
    @OneToMany
    @Column(nullable = false)
    private Address address;

    @OneToOne
    @Column(nullable = false)
    private Account account;
    */
}