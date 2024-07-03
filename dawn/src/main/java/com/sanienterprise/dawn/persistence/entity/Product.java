package com.sanienterprise.dawn.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @Column(nullable = false, length = 100)
    private String product_name;
    
    @Column(nullable = false, length = 1000)
    private String product_description;

    @Column(nullable = true, length = 100)
    private String style;
    
    @Column(nullable = true, length = 7, precision = 2)
    private Double width;
    
    @Column(nullable = true, length = 7, precision = 2)
    private Double length;
    
    @Column(nullable = true, length = 7, precision = 2)
    private Double height;
    
    @Column(nullable = false, length = 9, precision = 2)
    private Double price;
    
    @Column(nullable = false, length = 7)
    private Integer quantity;
    
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductStatus product_status;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_added;

    // - FK

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Image> images;

    public Product(
        String product_name, 
        String product_description, 
        String style, 
        Double width, 
        Double length,
        Double height, 
        Double price, 
        Integer quantity, 
        Category category, 
        ProductStatus product_status,
        Date date_added,
        List<Image> images
    ) {
        
        this.product_name = product_name;
        this.product_description = product_description;
        this.style = style;
        this.width = width;
        this.length = length;
        this.height = height;
        this.price = price;
        this.quantity = quantity;
        this.product_status = product_status;
        this.category = category;
        this.date_added = date_added;
        this.images = images;
    }

    public enum Category {
        TV_STAND("TV Stand"), 
        COFFEE_TABLE("Coffee Table"), 
        CONSOLE_TABLE("Console Table"), 
        OCCASIONAL_CHAIR("Occasional Chair"), 
        CARPET("Carpet"), 
        DINING_SET("Dining Set"), 
        BAR_CHAIR("Bar Chair"),
        LOVE_SEAT("Love Seat"),
        HEADBOARD("Headboard"),
        PEDESTAL("Pedestal"),
        DRESSING_TABLE("Dressing Table"),
        SLEEPER_COUCH("Sleeper Couch"),
        STUDY_TABLE("Study Table"),
        CHEST_OF_DRAWER("Chest of Drawer"),
        USED_FRIDGE("Used Fridge"),
        BATHROOM_CADDY("Bathroom Caddy");

        private final String displayName;

        Category(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum ProductStatus {
        AVAILABLE("Available"), 
        UNAVAILABLE("Unavailable"), 
        OUT_OF_STOCK("Out of stock"), 
        COMING_SOON("Coming soon");

        private final String displayStatus;

        ProductStatus(String displayStatus) {
            this.displayStatus = displayStatus;
        }

        public String getDisplayName() {
            return displayStatus;
        }
    }
}