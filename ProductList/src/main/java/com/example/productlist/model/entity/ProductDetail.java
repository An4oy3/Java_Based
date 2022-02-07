package com.example.productlist.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    private Long id;
    private String name;
    private String description;
    private int kcal;

    public ProductDetail(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.kcal = product.getKcal();
    }
}
