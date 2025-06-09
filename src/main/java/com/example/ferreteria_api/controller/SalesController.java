package com.example.ferreteria_api.controller;

import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.entity.Sales;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.global.GetResponse;
import com.example.ferreteria_api.service.CategoriesService;
import com.example.ferreteria_api.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping
    public ResponseEntity<GetResponse> getAll() {
        List<Sales> sales = salesService.getSales();

        if (sales.isEmpty()) {
            GetResponse response = new GetResponse(false, "No se encontraron sales activos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        GetResponse response = new GetResponse(true, "categories found successfully");
        response.addField("sales", sales);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponse> getById(@PathVariable("id") Long id) {
        Sales sales = salesService.getSalesById(id);

        GetResponse response = new GetResponse(true, "sales found successfully");
        response.addField("sales", sales);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Sales sales) {
        Sales saveSales = salesService.save(sales);
        ApiResponse response = new ApiResponse("successfully created", saveSales);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        salesService.delete(id);
    }

}
