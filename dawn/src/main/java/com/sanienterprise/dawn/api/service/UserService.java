package com.sanienterprise.dawn.api.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.entity.Account;
import com.sanienterprise.dawn.persistence.entity.Account.AccountStatus;
import com.sanienterprise.dawn.persistence.entity.Address;
import com.sanienterprise.dawn.persistence.entity.Admin;
import com.sanienterprise.dawn.persistence.entity.Admin.Permission;
import com.sanienterprise.dawn.persistence.entity.Cart;
import com.sanienterprise.dawn.persistence.entity.Customer;
import com.sanienterprise.dawn.persistence.entity.History;
import com.sanienterprise.dawn.persistence.entity.Image;
import com.sanienterprise.dawn.persistence.entity.Product;
import com.sanienterprise.dawn.persistence.entity.Product.Category;
import com.sanienterprise.dawn.persistence.entity.Product.ProductStatus;
import com.sanienterprise.dawn.persistence.entity.Wishlist;
import com.sanienterprise.dawn.persistence.repository.AdminRepository;
import com.sanienterprise.dawn.persistence.repository.CustomerRepository;
import com.sanienterprise.dawn.persistence.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userCustRepo;
    private UserRepository userAdminRepo;

    public UserService(
        CustomerRepository userCustRepo,
        AdminRepository userAdminRepo
    ) {
        this.userCustRepo = userCustRepo;
        this.userAdminRepo = userAdminRepo;
    }

    public boolean addAdminAndCustomer() {

        userAdminRepo.save(popAdmin());
        userCustRepo.save(popCustomer());

        return true;
    }

    private Admin popAdmin() {

        Admin admin = new Admin(
            "5423579432345", 
            "Admin", 
            "Administrator", 
            "admin@sep.com", 
            "+27 87 654 2345", 
            "admin", 
            "123", 
            Permission.READ_WRITE
        );

        return admin;

    }

    private Customer popCustomer() {

        String mysticChair ="c:\\Users\\Marvin\\Documents\\GitHub\\INTERNET-PROJECT-2024\\dawn\\src\\main\\resources\\static\\images\\mystic-1.webp" ;
        String zen = "c:\\Users\\Marvin\\Documents\\GitHub\\INTERNET-PROJECT-2024\\dawn\\src\\main\\resources\\static\\images\\Zen_Three_Bowl_Fountain.webp";
        String aurora = "C:\\Users\\Marvin\\Documents\\GitHub\\INTERNET-PROJECT-2024\\dawn\\src\\main\\resources\\static\\images\\Aurora.webp";
        String serenity = "c:\\Users\\Marvin\\Documents\\GitHub\\INTERNET-PROJECT-2024\\dawn\\src\\main\\resources\\static\\images\\SDSTY_LR.jpg";

        byte[] mysticChairBlob = null;
        byte[] zenBlob = null;
        byte[] auroraBlob = null;
        byte[] serenityBlob = null;

        try {
            mysticChairBlob = convertImageToByteArray(mysticChair);
            zenBlob = convertImageToByteArray(zen);
            auroraBlob = convertImageToByteArray(aurora);
            serenityBlob = convertImageToByteArray(serenity);
            
            // Now you can store the imageBytes in your database
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Image> images1 = new ArrayList<>();
        List<Image> images2 = new ArrayList<>();
        List<Image> images3 = new ArrayList<>();
        List<Image> images4 = new ArrayList<>();

        images1.add(new Image("Mystic Chair Image", mysticChairBlob));
        images2.add(new Image("Zen Garden Fountain Image", zenBlob));
        images3.add(new Image("Aurora Borealis Tapestry Image", auroraBlob));
        images4.add(new Image("Serenity Aromatherapy Diffuser", serenityBlob));

        List<Product> products = new ArrayList<>();

        // Assuming products is an ArrayList<Product> or another appropriate collection
        products.add(
            new Product(
                "Mystic Chair", 
                "The Mystic Chair embodies the essence of elegance, comfort, and mystique, offering more than just a place to sit; it's a sanctuary for relaxation and contemplation. Crafted with meticulous attention to detail and an unwavering commitment to quality, this chair seamlessly blends timeless design with contemporary flair, making it a captivating addition to any space.", 
                "Modern",
                10.99,
                45.67,
                31.22,800.99, 
                46, 
                Category.CHAIR,
                ProductStatus.COMING_SOON, 
                Date.from(Instant.now()),
                images1
            )
        );

        products.add(
            new Product(
                "Zen Garden Fountain",
                "Immerse yourself in tranquility with the Zen Garden Fountain...",
                "Modern",
                10.99,
                45.67,
                31.22,499.99,
                30,
                Category.SOFA,
                ProductStatus.AVAILABLE,
                Date.from(Instant.now()),
                images2
            )
        );

        products.add(
            new Product(
                "Aurora Borealis Tapestry",
                "Experience the awe-inspiring beauty of the Northern Lights...",
                "Modern",
                10.99,
                45.67,
                31.22,
                69.99,
                50,
                Category.BED,
                ProductStatus.AVAILABLE,
                new Date(),
                images3
            )
        );

        products.add(
            new Product(
                "Serenity Aromatherapy Diffuser",
                "Elevate your senses and create a peaceful ambiance with the Serenity Aromatherapy Diffuser...",
                "Modern",
                10.99,
                45.67,
                31.22,39.99,
                100,
                Category.CHAIR,
                ProductStatus.AVAILABLE,
                new Date(),
                images4
            )
        );

        Customer cust = new Customer(
            "2873649876386", 
            "Customer", 
            "Visitor", 
            "cust@sep.com", 
            "+27 12 345 6789", 
            12, 
            'M', 
            Date.from(Instant.now()), 
            Date.from(Instant.now()),
            new Address(
                "Maboe Street", 
                "Ga-Rankuwa", 
                "Pretoria", 
                "Gauteng", 
                "0208"
            ),
            new Account(
                "Username", 
                "321", 
                AccountStatus.PENDING_ACTIVATION, 
                new Wishlist(
                    
                ), 
                new Cart(
                    products
                ), 
                new History(

                )
            )
        );

        return cust;
    }

    public static byte[] convertImageToByteArray(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        FileInputStream fis = new FileInputStream(imageFile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = bos.toByteArray();

        fis.close();
        bos.close();

        return imageBytes;
    }

// -- GET BYTE[] FROM DATABASE AND CONVERT INTO AN IMAGE --

/* 
    import java.awt.image.BufferedImage;
    import java.io.ByteArrayInputStream;
    import java.io.File;
    import java.io.IOException;
    import javax.imageio.ImageIO;

    public class ByteToImageExample {
        public static void main(String[] args) {
            // Assuming you have a byte array representing an image
            byte[] imageData = getImageDataFromDatabase(); // Replace this with your byte array

            // Convert byte array to BufferedImage
            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageData)) {
                BufferedImage image = ImageIO.read(bis);

                // Write BufferedImage to file
                File output = new File("output.jpg"); // Change the file extension according to the image format
                ImageIO.write(image, "jpg", output); // Change the format according to the actual image format

                System.out.println("Image successfully written to file: " + output.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Dummy method to simulate fetching image data from database
        private static byte[] getImageDataFromDatabase() {
            // Replace this with actual logic to fetch image data from your database
            return new byte[]{ Your byte array representing the image };
        }
    } */

}
