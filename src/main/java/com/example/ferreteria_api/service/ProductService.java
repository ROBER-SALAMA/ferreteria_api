package com.example.ferreteria_api.service;

import com.example.ferreteria_api.DTO.CreateProductDTO;
import com.example.ferreteria_api.DTO.UpdateProductDto;
import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.entity.Product;
import com.example.ferreteria_api.entity.TypeOfMeasure;
import com.example.ferreteria_api.entity.TypeOfProduct;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.repository.CategoriesRepository;
import com.example.ferreteria_api.repository.ProductRepository;
import com.example.ferreteria_api.repository.TypeOfMeasureRepository;
import com.example.ferreteria_api.repository.TypeOfProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    TypeOfProductRepository typeOfProductRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    TypeOfMeasureRepository typeOfMeasureRepository;


    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private  TypeOfMeasureService typeOfMeasureService;

    @Autowired
    private  TypeOfProductService typeOfProductService;

    public List<Product> getProduct() {
        try {
            return productRepository.findAll()
                    .stream()
                    .filter(Product::isActive)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException(
                    "Error: error in obtaining the products " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public Product getProductById(Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Producto no encontrado con id: " + id, HttpStatus.NOT_FOUND));

            if (!product.isActive()) {
                throw new CustomException("Producto no encontrado", HttpStatus.NOT_FOUND);
            }

            return product;
        } catch (Exception e) {
            throw new CustomException(
                    "Error: error in obtaining thr product",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public Product save(CreateProductDTO dto) {
        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setCode(dto.getCode());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            product.setStock(dto.getStock());

            TypeOfMeasure measure = typeOfMeasureRepository.findById(dto.getTypeOfMeasureId())
                    .orElseThrow(() -> new RuntimeException("Tipo de medida no encontrado"));
            Categories category = categoriesRepository.findById(dto.getCategoriesId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            TypeOfProduct type = typeOfProductRepository.findById(dto.getTypeOfProductId())
                    .orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado"));

            product.setTypeOfMeasure(measure);
            product.setCategories(category);
            product.setTypeOfProduct(type);


            return productRepository.save(product);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method save from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void update(Long id, UpdateProductDto dto) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + id));

            System.out.println("DTO recibido: " + dto);
            if (dto.getTypeOfMeasureId() == null) {
                throw new CustomException("El campo 'typeOfMeasureId' es obligatorio", HttpStatus.BAD_REQUEST);
            }
            if (dto.getCategoriesId() == null) {
                throw new CustomException("El campo 'categoriesId' es obligatorio", HttpStatus.BAD_REQUEST);
            }
            if (dto.getTypeOfProductId() == null) {
                throw new CustomException("El campo 'typeOfProductId' es obligatorio", HttpStatus.BAD_REQUEST);
            }

            product.setName(dto.getName());
            product.setCode(dto.getCode());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());

            product.setTypeOfMeasure(typeOfMeasureService.getTypeOfMeasureById(dto.getTypeOfMeasureId()));
            product.setCategories(categoriesService.getCategoriesById(dto.getCategoriesId()));
            product.setTypeOfProduct(typeOfProductService.getTypeOfProductById(dto.getTypeOfProductId()));

            productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(
                    "Internal server error: method update from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void delete(Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("product no found id: " + id));
            if (product.isActive()){
                product.setActive(false);
                productRepository.save(product);
            }
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method delete from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
