package com.imdifoods.imdifoodswebcommerce.service;

import com.imdifoods.imdifoodswebcommerce.model.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Product saveProduct(Product product, MultipartFile imageFile);
}
