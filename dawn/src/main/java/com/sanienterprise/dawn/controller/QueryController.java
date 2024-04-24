package com.sanienterprise.dawn.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.api.service.ImageService;
import com.sanienterprise.dawn.persistence.entity.Image;

@Controller
@RequestMapping("/query")
public class QueryController {
    
    private ImageService imgServ;

    public QueryController(ImageService imgServ) {
        this.imgServ = imgServ;
    }
    
    @ModelAttribute(name = "image_ids")
    public List<Integer> getAllIds() {
        List<Integer> list = imgServ.getAllIds();

        return list;
    }
    
    @GetMapping
    public String getQuery() {
        return "query";
    }

    @PostMapping
    public String posQuery(
        @RequestParam("update_id") Integer id,
        @RequestParam("update_name") String name,
        @RequestParam("update_image") MultipartFile file
    ) {
        boolean flag = imgServ.updateImageById(id, name, file);

        String msg = "row not updated!";

        if(flag) {
            msg = "row updated!";
        }

        System.out.println("MSG: " + msg);

        return "redirect:/display?id=" + id;
    }
}
