package com.imdifoods.imdifoodswebcommerce.service;

import com.imdifoods.imdifoodswebcommerce.model.Product;
import com.imdifoods.imdifoodswebcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProductServiceImpl implements ProductService {

    public static String UPLOAD_DIRECTORY = (System.getProperty("user.dir") + File.separator + "uploads");
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product, MultipartFile imageFile) throws IOException{
        if (imageFile == null) {
            return product;
        }
        Files.createDirectories(Paths.get(UPLOAD_DIRECTORY));
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, String.valueOf(product.getId()));
        Files.write(fileNameAndPath, imageFile.getBytes());
        productRepository.save(product);
        return product;
    }
}
