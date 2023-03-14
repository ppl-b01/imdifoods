package com.imdifoods.imdifoodswebcommerce.controller;

import com.imdifoods.imdifoodswebcommerce.model.Product;
import com.imdifoods.imdifoodswebcommerce.service.CloudinaryService;
import com.imdifoods.imdifoodswebcommerce.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProductService productService;
    @MockBean
    private CloudinaryService cloudinaryService;

    private final String NAME = "mockName";
    private final String DESCRIPTION = "mockDescription";
    private final int STOCK = 3;
    private final Double PRICE = 10000.0;
    private final String IMAGE_ID = "imageId";
    private final MockMultipartFile FILE = new MockMultipartFile(
            "image",           // name of the file input field in the form
            "mockImage.jpg",   // original file name
            "multipart/form-data",     // content type of the file
            "mockContent".getBytes() // content of the file
    );

    @Test
    void whenImageUploadSuccess_ShouldSaveProduct() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart("/product/add")
                .file(FILE)
                .param("name", NAME)
                .param("description", DESCRIPTION)
                .param("stock", String.valueOf(STOCK))
                .param("price", String.valueOf(PRICE));

        when(cloudinaryService.uploadImage(FILE)).thenReturn(IMAGE_ID);
        mvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(cloudinaryService).uploadImage(FILE);
        verify(productService).saveProduct(NAME, DESCRIPTION, STOCK, PRICE, IMAGE_ID);
    }
}