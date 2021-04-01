package com.example.recrutation.pixel.repository;

import com.example.recrutation.pixel.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientsRepository extends JpaRepository<Patient, Integer> {
}
