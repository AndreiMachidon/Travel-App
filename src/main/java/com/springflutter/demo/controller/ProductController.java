package com.springflutter.demo.controller;


import com.springflutter.demo.entity.ImageModel;
import com.springflutter.demo.entity.Product;
import com.springflutter.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //we use MediaType to get the picture from the request
    //MultipartFile[] means we can upload multiple images at a time
    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile") MultipartFile[] files) {

        try {
            Set<ImageModel> images = uploadImage(files); //here we create the ImageModel objects
            product.setProductImages(images); //here we asign them to the product
             return productService.addNewProduct(product); //here we add the product with its images to the database
        }catch  (IOException e){
            System.out.println(e);
            return null;
        }


    }


    //this method takes images and creates ImageModel objects and returns a Set of ImageModel objects
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for(MultipartFile file : multipartFiles){
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }
}
