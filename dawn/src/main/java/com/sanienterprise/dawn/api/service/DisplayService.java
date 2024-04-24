package com.sanienterprise.dawn.api.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
