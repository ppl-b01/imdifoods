package com.imdifoods.imdifoodswebcommerce.service;

import com.imdifoods.imdifoodswebcommerce.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    private Product product;
    @BeforeEach
    void setUp() {
        product = Product.builder()
                .name("Ayam")
                .description("Makanan spesial")
                .stock(3)
                .price(10000)
                .build();
    }

    @Test
    void saveProduct_WithImage() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "image",           // name of the file input field in the form
                "mockImage.jpg",   // original file name
                "multipart/form-data",     // content type of the file
                "mockContent".getBytes() // content of the file
        );
        productService.saveProduct(product, file);
    }

    @Test
    void saveProduct_WithoutImage() throws IOException {
        MockMultipartFile file = null;
        productService.saveProduct(product, file);
    }
}