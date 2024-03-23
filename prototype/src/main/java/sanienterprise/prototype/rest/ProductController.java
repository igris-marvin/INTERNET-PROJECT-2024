package sanienterprise.prototype.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import sanienterprise.prototype.model.Product;

@Controller
@RequestMapping("/products")
@SessionAttributes("product")
public class ProductController {

    @ModelAttribute
    public void addProductToModel(Model model) {

        Product product = new Product();

        product.setProductID("STG");
        product.setName("Multi Layered Seat");
        product.setPrice("R999.99");
        product.setImgLink("/images/img1.jpg");

        model.addAttribute("product", product);
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
