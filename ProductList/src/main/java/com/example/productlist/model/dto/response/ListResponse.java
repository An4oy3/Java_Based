package com.example.productlist.model.dto.response;

import com.example.productlist.model.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListResponse {
    private String error;
    private Long id;
    private String name;
    private List<ProductDetail> products;
    private Long sumKcal;

    public ListResponse(com.example.productlist.model.entity.List list, String error){
        this.error = error;
        this.id = list.getId();
        this.name = list.getName();
        this.products = new ArrayList<>();
        this.sumKcal = 0L;

        if(list.getProducts() != null){
            list.getProducts().forEach(product -> {
                products.add(new ProductDetail(product));
                sumKcal += product.getKcal();
            });
        }
    }

}