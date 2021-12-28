package com.skillbox.mongodemo;

import java.util.HashSet;
import java.util.Objects;

public class Product {
    private String name;
    private int price;
    private static HashSet<Product> productList = new HashSet<>();

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        productList.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static HashSet<Product> getProductList() {
        return productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
