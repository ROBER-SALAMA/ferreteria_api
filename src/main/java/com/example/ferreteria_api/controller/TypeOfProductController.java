package com.example.ferreteria_api.controller;


import com.example.ferreteria_api.entity.TypeOfProduct;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.service.TypeOfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/typeofproduct")
public class TypeOfProductController {
    @Autowired
    private TypeOfProductService typeOfProductService;

    @GetMapping
    public List<TypeOfProduct> getAll() {
        return typeOfProductService.getTypeOfProduct();
    }

    @GetMapping("/{id}")
    public Optional<TypeOfProduct> getById(@PathVariable("id") Long id) {
        return typeOfProductService.getTypeOfProductById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody TypeOfProduct typeOfProduct) {
        TypeOfProduct saveTyOfProduct = typeOfProductService.save(typeOfProduct);
        ApiResponse response = new ApiResponse("successfully created", saveTyOfProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody TypeOfProduct typeOfProduct) {
        typeOfProduct.setId(id);
        typeOfProductService.update(typeOfProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        typeOfProductService.delete(id);
    }

}


