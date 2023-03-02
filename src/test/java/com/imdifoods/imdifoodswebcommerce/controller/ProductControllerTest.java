package com.imdifoods.imdifoodswebcommerce.controller;

import com.imdifoods.imdifoodswebcommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAddProductTest() throws Exception {
        // when
        mvc.perform(get("/product/add"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void saveProductTest() throws Exception {
        mvc.perform(post("/product/add")
                        .param("name","mockName")
                        .param("description","mockDescription")
                        .param("stock","1")
                        .param("price","2"))
                .andExpect(status().is2xxSuccessful());
    }
}