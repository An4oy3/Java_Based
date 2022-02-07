package com.example.productlist.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "lists")
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Product> products;
}
