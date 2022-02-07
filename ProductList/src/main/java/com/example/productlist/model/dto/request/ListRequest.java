package com.example.productlist.model.dto.request;

import com.example.productlist.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRequest {
    private String name;
    @JsonProperty("products_id")
    private List<Long> productsId;
}
