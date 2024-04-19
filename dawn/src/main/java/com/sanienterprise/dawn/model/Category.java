package com.sanienterprise.dawn.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Integer category_id;

    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private CategoryName categoryName; //TABLE, CHAIR

    @Column(nullable = true, length = 100)
    private String style;

    @Column(nullable = true, length = 7)
    private Double width;

    @Column(nullable = true, length = 7)
    private Double length;

    @Column(nullable = true, length = 7)
    private Double height;

    public enum CategoryName {
        CHAIR, TABLE, BED, WARDROBE, SOFA, CABINET, SHELVE, DESK;
    }
}
