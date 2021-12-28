package com.skillbox.mongodemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Store {
    private String name;
    private int rating;
    private Set<Product> products = new HashSet<>();

    public Store(String name) {
        this.name = name;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }


    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
