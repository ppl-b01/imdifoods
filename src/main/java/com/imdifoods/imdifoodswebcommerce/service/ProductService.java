package com.imdifoods.imdifoodswebcommerce.service;

import com.imdifoods.imdifoodswebcommerce.model.Product;

public interface ProductService {
    Product saveProduct(String name, String description, int stock, Double price, String imageId);
}
