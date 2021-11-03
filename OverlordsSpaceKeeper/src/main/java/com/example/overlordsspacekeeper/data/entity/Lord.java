package com.example.overlordsspacekeeper.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "lord")
public class Lord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long age;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lord")
    private List<Planet> planets;
}
