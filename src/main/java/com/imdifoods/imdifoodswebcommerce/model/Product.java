package com.imdifoods.imdifoodswebcommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Generated
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int stock;
    private int price;
    private String imageFile;

}
