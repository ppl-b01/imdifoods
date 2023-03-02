package com.imdifoods.imdifoodswebcommerce.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProductTest {

    @Test
    public void productBuilderTest() {
        Product product = Product.builder()
                .name("Ayam")
                .description("Makanan spesial")
                .stock(3)
                .price(10000)
                .build();
    }
}