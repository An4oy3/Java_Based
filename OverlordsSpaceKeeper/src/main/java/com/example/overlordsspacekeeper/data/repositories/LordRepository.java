package com.example.overlordsspacekeeper.data.repositories;

import com.example.overlordsspacekeeper.data.entity.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LordRepository extends JpaRepository<Lord, Long> {
    @Query(value = "select l from Lord l where l.planets is empty")
    List<Lord> findAllByPlanetsEmpty();

    @Query(value = "Select * from Lord ORDER BY age LIMIT 10", nativeQuery = true)
    List<Lord> findTopByAge();

}

