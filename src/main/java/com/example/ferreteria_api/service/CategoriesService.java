package com.example.ferreteria_api.service;

import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    public List<Categories> getCategories() {
        try {
            return categoriesRepository.findAll()
                    .stream()
                    .filter(Categories::isActive) // Filtra solo los activos
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new CustomException(
                    "Error al procesar los roles: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public Optional<Categories> getCategoriesById(Long id){
        try {
           return categoriesRepository.findById(id);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method findById from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    public Categories save(Categories categories) {
        try {
            return categoriesRepository.save(categories);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method save from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    public void update(Categories categories) {
        try {
            if (categories.getId() == null || !categoriesRepository.existsById(categories.getId())) {
                throw new IllegalArgumentException("Failed to update: role does not exist.");
            }
            categoriesRepository.save(categories);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method update from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void delete(Long id) {
        try {
            Categories categories = categoriesRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + id));

            if (categories.isActive()) {
                categories.setActive(false);
                categoriesRepository.save(categories);
            }
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method delete from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
