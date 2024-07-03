package sanienterprise.prototype.model;

import lombok.Data;

@Data
public class Product {
    private String productID;
    private String name;
    private String price;
    private String imgLink;

    public Product() {
    }

    public Product(String productID, String name, String price, String imgLink) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.imgLink = imgLink;
    }
}
