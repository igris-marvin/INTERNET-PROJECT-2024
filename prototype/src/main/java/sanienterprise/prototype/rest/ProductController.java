package sanienterprise.prototype.rest;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import sanienterprise.prototype.model.Product;

@Controller
@RequestMapping("/products")
@SessionAttributes("productList")
public class ProductController {

    @ModelAttribute
    public void addProductToModel(Model model) {

        ArrayList<Product> productList = new ArrayList<>();
            
        productList.add(new Product("CH01", "Chair", "R1000", "/images/img1.jpg"));
        productList.add(new Product("TB02", "Table", "R2000", "/images/img2.jpg"));
        productList.add(new Product("LM03", "Lamp", "R3000", "/images/img3.jpg"));
        productList.add(new Product("BS04", "Bookshelf", "R4000", "/images/img4.jpg"));
        productList.add(new Product("SF05", "Sofa", "R5000", "/images/img5.jpg"));
        productList.add(new Product("DK06", "Desk", "R6000", "/images/img6.jpg"));
        productList.add(new Product("MR07", "Mirror", "R7000", "/images/img7.jpg"));
        productList.add(new Product("BD08", "Bed", "R8000", "/images/img8.jpg"));
        productList.add(new Product("CB09", "Cabinet", "R9000", "/images/img9.jpg"));
        productList.add(new Product("RG10", "Rug", "R100000", "/images/img10.jpg"));

        model.addAttribute("productList", productList);
    }

    @ModelAttribute(name = "productList")
    public ArrayList<Product> getProductList() {
        return new ArrayList<Product>();
    }

    @ModelAttribute(name = "product")
    public Product getProduct() {
        return new Product();
    }

    @GetMapping("/seating")
    public String results() {
        return "results";
    }
}
