package com.imdifoods.imdifoodswebcommerce.service;

import com.imdifoods.imdifoodswebcommerce.model.Product;
import com.imdifoods.imdifoodswebcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(String name, String description, int stock, Double price, String imageId) {
        Product product = Product.builder()
                .name(name)
                .description(description)
                .stock(stock)
                .price(price)
                .imageId(imageId)
                .build();
        productRepository.save(product);
        return product;
    }

    @Override
    public Page<Product> getAllPageable(int page, int itemPerPage) {
        Pageable pageable = PageRequest.of(page-1, itemPerPage);
        return productRepository.findAll(pageable);
    }
}
