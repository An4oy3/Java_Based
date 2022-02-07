package com.example.productlist.model.dto.response;

import com.example.productlist.model.entity.List;
import com.example.productlist.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String error;
    private Long id;
    private String name;
    private String description;
    private int kcal;
    @JsonProperty("list_id")
    private Long listId;

    public ProductResponse(Product product, String error){
        this.error = error;
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.kcal = product.getKcal();
        this.listId = product.getList() != null ? product.getList().getId() : null;
    }
}
