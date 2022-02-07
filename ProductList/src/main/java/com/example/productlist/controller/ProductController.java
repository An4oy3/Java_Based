package com.example.productlist.controller;

import com.example.productlist.model.dto.request.ProductRequest;
import com.example.productlist.model.dto.response.ProductResponse;
import com.example.productlist.service.ProductService;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/")
@Api("Работа с продуктами")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    @ApiOperation("Получение продукта")
    public ResponseEntity<ProductResponse> getProduct(@RequestParam Long id){
        return productService.getProduct(id);
    }

    @PostMapping("")
    @ApiOperation("Добавление нового продукта")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request){
        return productService.addProduct(request);
    }

    @PutMapping("")
    @ApiOperation("Изменение списка у продукта")
    public ResponseEntity<ProductResponse> changeProductList(@RequestParam Long id, @RequestParam String listName){
        return productService.changeProductList(id, listName);
    }
}
