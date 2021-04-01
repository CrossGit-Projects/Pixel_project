package com.example.recrutation.pixel.repository;

import com.example.recrutation.pixel.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitsRepository extends JpaRepository<Visit, Integer> {
}
