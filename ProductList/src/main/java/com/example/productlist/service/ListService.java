package com.example.productlist.service;

import com.example.productlist.exception.EntityAlreadyExists;
import com.example.productlist.exception.EntityNotFoundException;
import com.example.productlist.model.dto.request.ListRequest;
import com.example.productlist.model.dto.response.ListResponse;
import com.example.productlist.model.entity.List;
import com.example.productlist.model.entity.Product;
import com.example.productlist.model.repository.ListRepository;
import com.example.productlist.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ListRepository listRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<ListResponse> getList(Long id) {
        List list = listRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Invalid List Id"));
        return ResponseEntity.ok(new ListResponse(list, "ok"));
    }

    public ResponseEntity<ListResponse> addList(ListRequest request){
        if(listRepository.findByName(request.getName()) != null){
            throw new EntityAlreadyExists("List already exists");
        }
        List list = new List();
        list.setName(request.getName());

        java.util.List<Product> products = new ArrayList<>();
        if(request.getProductsId() != null && request.getProductsId().size() > 0) {
            products = productRepository.findAllById(request.getProductsId());
            list.setProducts(products);
        }

        List newList = listRepository.save(list);
        for (Product product : products) {
            product.setList(newList);
            productRepository.save(product);
        }

        return ResponseEntity.ok(new ListResponse(newList, "ok"));
    }
}
