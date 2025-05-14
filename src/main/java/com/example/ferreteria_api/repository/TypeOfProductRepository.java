package com.example.ferreteria_api.repository;

import com.example.ferreteria_api.entity.TypeOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfProductRepository extends JpaRepository<TypeOfProduct,Long> {

}
