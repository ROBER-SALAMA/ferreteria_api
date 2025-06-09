package com.example.ferreteria_api.repository;

import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> { }
