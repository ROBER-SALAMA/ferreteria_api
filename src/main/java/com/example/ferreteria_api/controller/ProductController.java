package com.example.ferreteria_api.controller;

import com.example.ferreteria_api.DTO.CreateProductDTO;
import com.example.ferreteria_api.DTO.UpdateProductDto;
import com.example.ferreteria_api.entity.Product;
import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.global.GetResponse;
import com.example.ferreteria_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<GetResponse> getAll() {
        List<Product> products = productService.getProduct();

        if (products.isEmpty()) {
            GetResponse response = new GetResponse(false, "No se encontraron roles activos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        GetResponse response = new GetResponse(true, "Roles found successfully");
        response.addField("products", products);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponse> getById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);

        GetResponse response = new GetResponse(true, "product found successfully");
        response.addField("products", product);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody CreateProductDTO createProductDTO) {
        Product saveProduct = productService.save(createProductDTO);
        ApiResponse response = new ApiResponse("successfully created", saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateProductDto updateProductDto
    ) {
        productService.update(id, updateProductDto);
        ApiResponse response = new ApiResponse("successfully updated", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }


}
