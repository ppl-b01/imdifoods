package com.imdifoods.imdifoodswebcommerce.service;

import com.imdifoods.imdifoodswebcommerce.model.Product;
import com.imdifoods.imdifoodswebcommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    private Product product;
    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .name("Ayam")
                .description("Makanan spesial")
                .stock(3)
                .price(10000)
                .build();
    }

    @Test
    public void saveProduct_WithImage() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "image",           // name of the file input field in the form
                "mockImage.jpg",   // original file name
                "multipart/form-data",     // content type of the file
                "mockContent".getBytes() // content of the file
        );
        productService.saveProduct(product, file);
        verify(productRepository).save(product);
    }

    @Test
    public void saveProduct_WithoutImage() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "image",           // name of the file input field in the form
                "mockImage.jpg",   // original file name
                "multipart/form-data",     // content type of the file
                "".getBytes() // content of the file
        );
        productService.saveProduct(product, file);
        verify(productRepository, times(0)).save(product);
    }
}