package com.example.ferreteria_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String code;

    @Column(name = "price", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;

    private String description;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "type_of_measure_id")
    private TypeOfMeasure typeOfMeasure;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "type_of_product_id")
    private TypeOfProduct typeOfProduct;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive = true;

}
