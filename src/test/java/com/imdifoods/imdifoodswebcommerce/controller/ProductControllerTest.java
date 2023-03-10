package com.imdifoods.imdifoodswebcommerce.controller;

import com.imdifoods.imdifoodswebcommerce.service.CloudinaryService;
import com.imdifoods.imdifoodswebcommerce.service.ProductService;
import com.imdifoods.imdifoodswebcommerce.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CloudinaryService cloudinaryService;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAddProductTest() throws Exception {
        mvc.perform(get("/product/add"))
                .andExpect(status().isOk());
    }

    @Test
    void whenImageUploadFail_ShouldDisplayPreviousData() throws Exception {
        String name = "mockName";
        String description = "mockDescription";
        int stock = 3;
        Double price = 10000.0;

        MockMultipartFile file = new MockMultipartFile(
                "image",           // name of the file input field in the form
                "mockImage.jpg",   // original file name
                "multipart/form-data",     // content type of the file
                "mockContent".getBytes() // content of the file
        );

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart("/product/add")
                .file(file)
                .param("name",name)
                .param("description",description)
                .param("stock",String.valueOf(stock))
                .param("price",String.valueOf(price));

        mvc.perform(requestBuilder)
                .andExpect(status().isOk());
        verify(cloudinaryService).uploadImage(file);
    }

    @Test
    void whenImageUploadSuccess_ShouldSaveProduct() throws Exception {
        String name = "mockName";
        String description = "mockDescription";
        int stock = 3;
        Double price = 10000.0;
        String imageId = "imageId";

        MockMultipartFile file = new MockMultipartFile(
                "image",           // name of the file input field in the form
                "mockImage.jpg",   // original file name
                "multipart/form-data",     // content type of the file
                "mockContent".getBytes() // content of the file
        );

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart("/product/add")
                .file(file)
                .param("name",name)
                .param("description",description)
                .param("stock",String.valueOf(stock))
                .param("price",String.valueOf(price));

        when(cloudinaryService.uploadImage(file)).thenReturn(imageId);
        mvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(cloudinaryService).uploadImage(file);
        verify(cloudinaryService).getImageUrl(imageId);
        verify(productService).saveProduct(anyString(),anyString(),anyInt(),anyDouble(),anyString());
    }

    @Test
    @WithMockUser
    void testGetAdminProductsPage() throws Exception {
        Page<Product> mockProduct = new PageImpl<>(new ArrayList<>());
        when(productService.getAllPageable(anyInt(),anyInt())).thenReturn(mockProduct);

        mvc.perform(get("/product"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testGetAdminProductPageLessThanOnePageRedirectToFirstPage() throws Exception {
        Page<Product> mockProduct = new PageImpl<>(new ArrayList<>());
        when(productService.getAllPageable(anyInt(),anyInt())).thenReturn(mockProduct);

        mvc.perform(get("/product?page=0"))
                .andExpect(redirectedUrl("/product?page=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    void testGetAdminProductPageOverflowPageRedirectToLastPage() throws Exception {
        Page<Product> mockProduct = new PageImpl<>(new ArrayList<>());
        when(productService.getAllPageable(anyInt(),anyInt())).thenReturn(mockProduct);

        mvc.perform(get("/product?page=100"))
                .andExpect(redirectedUrl("/product?page=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(status().isFound());
    }
}