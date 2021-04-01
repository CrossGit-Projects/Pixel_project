package com.example.recrutation.pixel.repository;

import com.example.recrutation.pixel.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PractitionersRepository extends JpaRepository<Practitioner, Integer> {

}
