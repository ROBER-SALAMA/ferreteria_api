package com.example.ferreteria_api.repository;

import com.example.ferreteria_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
