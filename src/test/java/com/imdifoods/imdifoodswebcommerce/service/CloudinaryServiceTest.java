package com.imdifoods.imdifoodswebcommerce.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.Url;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@SpringBootTest
class CloudinaryServiceTest {
    @Autowired
    private CloudinaryService cloudinaryService;
    @MockBean
    private Cloudinary cloudinary;

    @Test
    void uploadImageShouldReturnPublicId() {
        MultipartFile imageFile = mock(MultipartFile.class);
        when(cloudinary.uploader()).thenReturn(mock(Uploader.class));
        String imageId = cloudinaryService.uploadImage(imageFile);
        assertNull(imageId);
    }


    @Test
    void uploadInvalidImageShouldReturnNull() {
        MultipartFile imageFile = null;
        String imageId = cloudinaryService.uploadImage(imageFile);
        assertNull(imageId);
    }

    @Test
    void getInvalidImageUrlShouldThrowException() {
        String imageId = null;
        assertNull(cloudinaryService.getImageUrl(imageId));
    }

    @Test
    void getValidImageUrlShouldReturnNotNull() {
        String imageId = "imageId";
        Url mockUrl = mock(Url.class);
        when(cloudinary.url()).thenReturn(mockUrl);
        when(mockUrl.secure(anyBoolean())).thenReturn(mockUrl);
        when(mockUrl.format(anyString())).thenReturn(mockUrl);
        when(mockUrl.publicId(anyString())).thenReturn(mockUrl);
        when(mockUrl.generate()).thenReturn(imageId);
        String url = cloudinaryService.getImageUrl(imageId);
        assertNotNull(url);
    }
}