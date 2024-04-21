package com.sanienterprise.dawn.api.service;

import java.util.Base64;

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

    public String getImage() {
        Image image = imgRepo.findById(1).get();

        byte[] image_bytes = image.getImage_source();

        String encoded_image = Base64.getEncoder().encodeToString(image_bytes);

        return encoded_image;
    }
}
