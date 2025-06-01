package com.example.ferreteria_api.service;

import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.entity.TypeOfProduct;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.repository.RolesRepository;
import com.example.ferreteria_api.repository.TypeOfProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeOfProductService {

    @Autowired
    TypeOfProductRepository typeOfProductRepository;

    public List<TypeOfProduct> getTypeOfProduct() {
        try {
            return typeOfProductRepository.findAll()
                    .stream()
                    .filter(TypeOfProduct::isActive)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new CustomException(
                    "Error al procesar los TypeOfProduct: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public TypeOfProduct getTypeOfProductById(Long id) {
        try {
            TypeOfProduct typeOfProduct = typeOfProductRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Tipo de productos con id: " + id, HttpStatus.NOT_FOUND));

            if (!typeOfProduct.isActive()) {
                throw new CustomException("tipo de producto con id " + id + " está inactivo", HttpStatus.NOT_FOUND);
            }

            return typeOfProduct;
        } catch (CustomException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Internal server error: method findById from service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public TypeOfProduct save(TypeOfProduct typeOfProduct) {
        try {
            return typeOfProductRepository.save(typeOfProduct);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method save from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    public void update(TypeOfProduct typeOfProduct) {
        try {
            if (typeOfProduct.getId() == null || !typeOfProductRepository.existsById(typeOfProduct.getId())) {
                throw new IllegalArgumentException("Failed to update: type of product does not exist.");
            }
            typeOfProductRepository.save(typeOfProduct);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method update from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void delete(Long id) {
        try {
            TypeOfProduct typeOfProduct = typeOfProductRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado con id: " + id));

            if (typeOfProduct.isActive()) {
                typeOfProduct.setActive(false);
                typeOfProductRepository.save(typeOfProduct);
            }
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method delete from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}

