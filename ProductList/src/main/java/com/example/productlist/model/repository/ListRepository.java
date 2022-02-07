package com.example.productlist.model.repository;

import com.example.productlist.model.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
    List findByName(String name);
}
