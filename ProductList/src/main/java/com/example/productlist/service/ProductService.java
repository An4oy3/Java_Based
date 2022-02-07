package com.example.productlist.service;

import com.example.productlist.exception.EntityNotFoundException;
import com.example.productlist.model.dto.request.ProductRequest;
import com.example.productlist.model.dto.response.ProductResponse;
import com.example.productlist.model.entity.List;
import com.example.productlist.model.entity.Product;
import com.example.productlist.model.repository.ListRepository;
import com.example.productlist.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ListRepository listRepository;


    public ResponseEntity<ProductResponse> getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Invalid Product Id"));
        return ResponseEntity.ok(new ProductResponse(product, "ok"));
    }


    public ResponseEntity<ProductResponse> addProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setKcal(request.getKcal());
        product.setList(listRepository.findByName(request.getListName()));
        Product newProduct = productRepository.save(product);

        return ResponseEntity.ok(new ProductResponse(newProduct, "ok"));
    }

    public ResponseEntity<ProductResponse> changeProductList(Long id, String listName) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Invalid Product Id"));
        List list = listRepository.findByName(listName);
        if(list == null)
            throw new EntityNotFoundException("Invalid List Name");

        product.setList(list);
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(new ProductResponse(newProduct, "ok"));
    }
}
