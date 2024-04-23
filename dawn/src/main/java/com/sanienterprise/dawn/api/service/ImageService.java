package com.sanienterprise.dawn.api.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.persistence.entity.Image;
import com.sanienterprise.dawn.persistence.repository.ImageRepository;

@Service
public class ImageService {
    
    private ImageRepository imgRepo;

    public ImageService(ImageRepository imgRepo) {
        this.imgRepo = imgRepo;
    }

    public boolean uploadImage(MultipartFile file) {
        Image image = new Image();

        image.setImageName("uploaded image");
        try {
            image.setImage_source(file.getBytes());

            imgRepo.save(image);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
