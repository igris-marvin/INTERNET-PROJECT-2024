package com.sanienterprise.dawn.api.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.entity.Image;
import com.sanienterprise.dawn.persistence.repository.ImageRepository;

@Service
public class DisplayService {
    
    @Autowired
    private ImageRepository imgRepo;

    public DisplayService(ImageRepository imgRepo) {
        this.imgRepo = imgRepo;
    }

    public String getImage(int number) {

        System.out.println("FLAG: " + imgRepo.existsById(number));

        String encoded_image = Base64
                                .getEncoder()
                                .encodeToString(
                                    imgRepo
                                        .findById(
                                            number
                                        )
                                .get()
                                .getImage_source());

        return encoded_image;
    }

    public List<String> getAllImages() {
        
        List<Image> image_array = imgRepo.findAll();

        List<String> images = new ArrayList<>();

        String encoded_image = "";

        for(Image x: image_array) {
            encoded_image = Base64
                .getEncoder()
                .encodeToString(
                    x.getImage_source()
                );

            images.add(encoded_image);
        }

        return images;
    }
}
