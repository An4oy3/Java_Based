package com.example.overlordsspacekeeper.data.repositories;

import com.example.overlordsspacekeeper.data.entity.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LordRepository extends JpaRepository<Lord, Long> {
    @Query(value = "select l from Lord l where l.planets is empty")
    List<Lord> findAllByPlanetsEmpty();

    @Query(value = "Select * from Lord l ORDER BY age LIMIT 10", nativeQuery = true)
    List<Lord> findTopByAge();
}

