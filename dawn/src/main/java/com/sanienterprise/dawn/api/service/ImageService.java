package com.sanienterprise.dawn.api.service;

import java.io.IOException;
import java.util.List;

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

    public Image getImage(int i) {

        Image image = imgRepo.findById(i).get();

        return image;
    }

    public List<Integer> getAllIds() {
        List<Integer> list = imgRepo.findAllId();

        return list;
    }

    public boolean updateImageById(
        Integer id, 
        String name, 
        MultipartFile file
    ) {
        boolean flag = false;

        try {
            byte[] source = file.getBytes();

            int num_of_affected_rows = imgRepo.updateImageById(name, source, id);

            if(num_of_affected_rows == 1) {
                flag = true;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return flag;
    }
}
