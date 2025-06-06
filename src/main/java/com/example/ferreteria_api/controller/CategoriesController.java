package com.example.ferreteria_api.controller;

import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.global.GetResponse;
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
    public ResponseEntity<GetResponse> getAll() {
        List<Categories> categories = categoriesService.getCategories();

        if (categories.isEmpty()) {
            GetResponse response = new GetResponse(false, "No se encontraron categorias activos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        GetResponse response = new GetResponse(true, "categories found successfully");
        response.addField("categories", categories);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponse> getById(@PathVariable("id") Long id) {
        Categories categories = categoriesService.getCategoriesById(id);

        GetResponse response = new GetResponse(true, "Roles found successfully");
        response.addField("categories", categories);

        return ResponseEntity.ok(response);
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
