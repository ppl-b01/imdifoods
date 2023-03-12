package com.imdifoods.imdifoodswebcommerce.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String uploadImage(MultipartFile file);
    String getImageUrl(String imageId);
}