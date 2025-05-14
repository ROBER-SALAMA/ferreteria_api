package com.example.ferreteria_api.controller;

import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public List<Categories> getAll() {
        return categoriesService.getCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getById(@PathVariable("id") Long id) {
        return categoriesService.getCategoriesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Categories categories) {
        Categories saveCategories = categoriesService.save(categories);
        ApiResponse response = new ApiResponse("successfully created", saveCategories);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Categories categories) {
        categories.setId(id);
        categoriesService.update(categories);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoriesService.delete(id);
    }

}
