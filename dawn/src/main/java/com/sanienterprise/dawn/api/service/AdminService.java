package com.sanienterprise.dawn.api.service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.api.dto.AdminDTO;
import com.sanienterprise.dawn.api.dto.CategoryCountDTO;
import com.sanienterprise.dawn.api.dto.CreateProductDTO;
import com.sanienterprise.dawn.api.dto.CustomerDashDTO;
import com.sanienterprise.dawn.api.dto.ModifyImageDTO;
import com.sanienterprise.dawn.api.dto.ModifyProductDTO;
import com.sanienterprise.dawn.api.dto.ProductDTO;
import com.sanienterprise.dawn.api.dto.RegisterAdminDTO;
import com.sanienterprise.dawn.persistence.entity.Admin;
import com.sanienterprise.dawn.persistence.entity.Customer;
import com.sanienterprise.dawn.persistence.entity.Image;
import com.sanienterprise.dawn.persistence.entity.Patron;
import com.sanienterprise.dawn.persistence.entity.Product;
import com.sanienterprise.dawn.persistence.entity.Product.Category;
import com.sanienterprise.dawn.persistence.entity.Product.ProductStatus;
import com.sanienterprise.dawn.persistence.repository.ImageRepository;
import com.sanienterprise.dawn.persistence.repository.PatronRepository;
import com.sanienterprise.dawn.persistence.repository.ProductRepository;

@Service
public class AdminService {
    
    @Autowired
    private ProductRepository proRepo;

    @Autowired
    private PatronRepository patRepo;

    @Autowired
    private ImageRepository imgRepo;

    public int getNumberOfProducts() {
        int count = Integer.parseInt(Long.toString(proRepo.count()));

        return count;
    }

    public int getNumberOfUsers() {
        
        int count = patRepo.countPatronByRole("CUSTOMER");

        return count;
    }

    public CategoryCountDTO getCategoryCountListObject() {
        CategoryCountDTO count_dto = new CategoryCountDTO();

        List<Product> prod_list = proRepo.findAll();

        count_dto.setBar_chair_count(getCategoryCount(Product.Category.BAR_CHAIR, prod_list));
        count_dto.setBathroom_caddy_count(getCategoryCount(Product.Category.BATHROOM_CADDY, prod_list));
        count_dto.setCarpet_count(getCategoryCount(Product.Category.CARPET, prod_list));
        count_dto.setChest_of_drawer_count(getCategoryCount(Product.Category.CHEST_OF_DRAWER, prod_list));
        count_dto.setCoffee_table_count(getCategoryCount(Product.Category.COFFEE_TABLE, prod_list));
        count_dto.setConsole_table_count(getCategoryCount(Product.Category.CONSOLE_TABLE, prod_list));
        count_dto.setDining_set_count(getCategoryCount(Product.Category.DINING_SET, prod_list));
        count_dto.setDressing_table_count(getCategoryCount(Product.Category.DRESSING_TABLE, prod_list));
        count_dto.setHeadboard_count(getCategoryCount(Product.Category.HEADBOARD, prod_list));
        count_dto.setLove_seat_count(getCategoryCount(Product.Category.LOVE_SEAT, prod_list));
        count_dto.setOccasional_chair_count(getCategoryCount(Product.Category.OCCASIONAL_CHAIR, prod_list));
        count_dto.setPedestal_count(getCategoryCount(Product.Category.PEDESTAL, prod_list));
        count_dto.setSleeper_couch_count(getCategoryCount(Product.Category.SLEEPER_COUCH, prod_list));
        count_dto.setStudy_table_count(getCategoryCount(Product.Category.STUDY_TABLE, prod_list));
        count_dto.setTv_stand_count(getCategoryCount(Product.Category.TV_STAND, prod_list));
        count_dto.setUsed_fridge_count(getCategoryCount(Product.Category.USED_FRIDGE, prod_list));

        return count_dto;
    }

    private int getCategoryCount(Category categoryType, List<Product> prod_list) {
        int count = 0;

        for(Product x: prod_list) {

            if(categoryType.name().equalsIgnoreCase(x.getCategory().name())) {
                count++;
            }
        }

        return count;
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();

        List<Product> prods = proRepo.findAll();

        for(Product x: prods) {
            products.add(genProductInfo(x));
        }

        return products;
    }

    private ProductDTO genProductInfo(Product x) {
        Integer id = x.getProduct_id();
        String product_image = "data:image/png;base64," 
                + Base64
                    .getEncoder()
                    .encodeToString(
                        x
                            .getImages()
                            .get(0)
                            .getImage_source()
                    );
        String product_title = x.getProduct_name();
        String product_category = x.getCategory().getDisplayName();
        String product_price = Double.toString(x.getPrice());
        String product_quantity = Integer.toString(x.getQuantity());
        String product_added_date = x.getDate_added().toString();

        return new ProductDTO(id, product_image, product_title, product_category, product_price, product_quantity, product_added_date);
    }

    public void removeProductById(Integer id) {
        proRepo.deleteById(id);
    }

    public List<CustomerDashDTO> getAllCustomers() {
        List<Patron> c = patRepo.findAll();

        List<CustomerDashDTO> custs = new ArrayList<>();

        for(Patron x: c) {

            if(x instanceof Customer) {

                custs.add(creatgenCustDTO(x));
            }
        }

        return custs;
    }

    private CustomerDashDTO creatgenCustDTO(Patron x) {

        Customer c = (Customer) x;

        Integer user_id = c.getUser_id();
        String username = c.getAccount().getUsername();
        String surname = c.getSurname();
        String name = c.getName();
        String email = c.getEmail();
        String date_added = c.getDate_added().toString();

        return new CustomerDashDTO(user_id, username, name, surname, email, date_added);
    }

    public ProductDTO getProductById(Integer id) {
        Product product = null;
        
        if(proRepo.existsById(id)) {
            product = proRepo.findById(id).get();
        } else {
            return null;
        }

        ProductDTO prod = genProductInfo(product);

        return prod;
    }

    public List<String> getProductStatuses() {
        List<String> list = new ArrayList<>();

        Product.ProductStatus[] array = Product.ProductStatus.values();

        for(Product.ProductStatus x: array) {
            list.add(x.getDisplayName());
        }

        return list;
    }

    public List<String> getProductCategories() {
        List<String> list = new ArrayList<>();

        Product.Category[] array = Product.Category.values();

        for(Product.Category x: array) {
            list.add(x.getDisplayName());
        }

        return list;
    }

    public boolean createProduct(CreateProductDTO product, List<MultipartFile> files) {
        boolean flag = false;

        Product pro = new Product();

        pro.setCategory(genProductCategory(product.getC_category()));
        pro.setDate_added(Date.from(Instant.now()));
        pro.setHeight(product.getHeight());
        pro.setImages(genImageBytes(files));
        pro.setLength(product.getLength());
        pro.setPrice(product.getPrice());
        pro.setProduct_description(product.getProduct_description());
        pro.setProduct_name(product.getProduct_name());
        pro.setProduct_status(genProductStatus(product.getS_status()));
        pro.setQuantity(product.getQuantity());
        pro.setStyle(product.getStyle());
        pro.setWidth(product.getWidth());

        proRepo.save(pro);

        flag = true;

        return flag;
    }

    private ProductStatus genProductStatus(String s_status) {
        Product.ProductStatus[] list = Product.ProductStatus.values();

        for(ProductStatus x: list) {
            if(x.getDisplayName().equalsIgnoreCase(s_status)) {
                return x;
            }   
        }

        return null;
    }

    private List<Image> genImageBytes(List<MultipartFile> files) {
        List<Image> list = new ArrayList<>();

        try {

            for(MultipartFile x: files) {
    
                String imageName = x.getOriginalFilename();
                byte[] image_source = x.getBytes();
    
                Image image = new Image(imageName, image_source);

                list.add(image);
            }

            return list;

        } catch(IOException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    private Category genProductCategory(String c_category) {
        Product.Category[] list = Product.Category.values();

        for(Category x: list) {
            if(x.getDisplayName().equalsIgnoreCase(c_category)) {
                return x;
            }   
        }

        return null;
    }

    public ModifyProductDTO getModifiableProductObject(Integer id) {
        
        Product pro = proRepo.findById(id).get();

        Integer m_product_id = pro.getProduct_id();
        String m_product_name = pro.getProduct_name();
        String m_product_description = pro.getProduct_description();
        String m_style = pro.getStyle();
        Double m_width = pro.getWidth();
        Double m_length = pro.getLength();
        Double m_height = pro.getHeight();
        Double m_price = pro.getPrice();
        Integer m_quantity = pro.getQuantity();
        String m_category = pro.getCategory().getDisplayName();
        String m_status = pro.getProduct_status().getDisplayName();

        List<ModifyImageDTO> image_list = getModifiableProductImages(pro.getImages());

        ModifyProductDTO dto = new ModifyProductDTO(m_product_id, m_product_name, m_product_description, m_style, m_width, m_length, m_height, m_price, m_quantity, m_category, m_status, image_list);
    
        return dto;
    }

	public List<ModifyImageDTO> getModifiableProductImages(List<Image> images) {
        List<ModifyImageDTO> list = new ArrayList<>();

        Integer image_id = 0;
        String image_data = "";

        for (Image image : images) {
            
            image_id = image.getImage_id();
            image_data = "data:image/png;base64," + Base64
                                                        .getEncoder()
                                                        .encodeToString(
                                                            image
                                                                .getImage_source()
                                                        );


            ModifyImageDTO dto = new ModifyImageDTO(image_id, image_data);
            
            list.add(dto);
        }

        return list;
    }

    public void updateProduct(
        Integer p_id, String p_name, String p_style, 
        Double p_length, Double p_width, Double p_height, 
        Double p_price, Integer p_quantity, String p_category, 
        String p_status, String p_description, List<MultipartFile> image_files
    ) {
        Product product = proRepo.findById(p_id).get();

        removeImages(product.getImages());

        product.setProduct_name(p_name);
        product.setProduct_description(p_description);
        product.setStyle(p_style);
        product.setWidth(p_width);
        product.setLength(p_length);
        product.setHeight(p_height);
        product.setPrice(p_price);
        product.setQuantity(p_quantity);
        product.setCategory(genProductCategory(p_category));
        product.setProduct_status(genProductStatus(p_status));
        
        List<Image> images = genImageBytes(image_files);

        product.setImages(images);

        proRepo.save(product);
    }

    private void removeImages(List<Image> images) {
        
        for (Image x: images) {
            imgRepo.removeImageById(x.getImage_id());
        }
    }

    public AdminDTO getAdminDetails(String usrn) {
        Admin obj = (Admin) patRepo.findPatronByUsername(usrn);
        
        String id = obj.getUser_id().toString();
        String username = obj.getAdmin_username();
        String name = obj.getName();
        String surname = obj.getSurname();
        String contact = obj.getContact_number();
        String email = obj.getEmail();
        String image = "data:image/png;base64," + Base64
                                                    .getEncoder()
                                                    .encodeToString(
                                                        obj.getAdmin_image()
                                                    );
        AdminDTO admin = new AdminDTO(
            id, 
            username, 
            name, 
            surname, 
            contact, 
            email, 
            image
        );

        return admin;
    }

    public boolean registerAdmin(RegisterAdminDTO admin, MultipartFile file) {

        String id_number = admin.getId_number();
        String name = admin.getName();
        String surname = admin.getSurname();
        String email = admin.getEmail();
        String contact = admin.getContact();
        String username = admin.getUsername();
        String password = admin.getPassword();

        String role = "ADMIN";

        byte[] image = null;

        try {
            image = file.getBytes();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }

        Admin obj = new Admin(id_number, name, surname, email, contact, role, username, password, image);

        patRepo.save(obj);

        return true;
    }

    public List<ProductDTO> getProductsByCategory(String category) {
        List<ProductDTO> prods = new ArrayList<>();

        List<Product> list = proRepo.findAll();

        for (Product x : list) {
            
            if(x.getCategory().name().equalsIgnoreCase(category)) {
                prods.add(genProductInfo(x));
            }
        }

        return prods;
    }
}
