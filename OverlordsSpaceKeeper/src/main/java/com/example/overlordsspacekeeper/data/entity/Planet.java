package com.example.overlordsspacekeeper.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "lord_id", referencedColumnName = "id")
    private Lord lord;
}
