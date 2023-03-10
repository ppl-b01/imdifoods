package com.imdifoods.imdifoodswebcommerce.service;

import com.imdifoods.imdifoodswebcommerce.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product saveProduct(String name, String description, int stock, Double price, String imageId);

    Page<Product> getAllPageable(int page, int itemPerPage);
}
