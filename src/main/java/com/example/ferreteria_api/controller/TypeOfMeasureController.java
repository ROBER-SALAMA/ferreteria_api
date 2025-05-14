package com.example.ferreteria_api.controller;


import com.example.ferreteria_api.entity.TypeOfMeasure;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.service.TypeOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/typeofmeasure")
public class TypeOfMeasureController {
    @Autowired
    private TypeOfMeasureService typeOfMeasureService;

    @GetMapping
    public List<TypeOfMeasure> getAll() {
        return typeOfMeasureService.getTypeOfMeasure();
    }

    @GetMapping("/{id}")
    public Optional<TypeOfMeasure> getById(@PathVariable("id") Long id) {
        return typeOfMeasureService.getTypeOfMeasureById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody TypeOfMeasure typeOfMeasure) {
        TypeOfMeasure saveTypeOfMeasure = typeOfMeasureService.save(typeOfMeasure);
        ApiResponse response = new ApiResponse("successfully created", saveTypeOfMeasure);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody TypeOfMeasure typeOfMeasure) {
        typeOfMeasure.setId(id);
        typeOfMeasureService.update(typeOfMeasure);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        typeOfMeasureService.delete(id);
    }

}


