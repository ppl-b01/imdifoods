package com.imdifoods.imdifoodswebcommerce.controller;

import com.imdifoods.imdifoodswebcommerce.service.CloudinaryService;
import com.imdifoods.imdifoodswebcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/add")
    public String getAddProduct() {
        return "createProduct";
    }

    @PostMapping("/add")
    public String saveProduct(Model model,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("stock") int stock,
                              @RequestParam("price") Double price,
                              @RequestParam("image") MultipartFile imageFile) {
        String imageId = cloudinaryService.uploadImage(imageFile);
        String imageUrl = cloudinaryService.getImageUrl(imageId);
        if (imageId == null) {
            model.addAttribute("msg", "Tolong submit ulang gambar produk.");
            model.addAttribute("name", name);
            model.addAttribute("description", description);
            model.addAttribute("stock", stock);
            model.addAttribute("price", price);
        } else {
            productService.saveProduct(name, description, stock, price, imageId);
            model.addAttribute("imageUrl", imageUrl);
        }
        return "createProduct";
    }
}
