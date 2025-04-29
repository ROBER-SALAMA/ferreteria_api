package com.example.ferreteria_api.repository;

import com.example.ferreteria_api.entity.TypeOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfMeasureRepository extends JpaRepository<TypeOfMeasure, Long> {}
