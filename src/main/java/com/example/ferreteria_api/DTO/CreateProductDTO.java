package com.example.ferreteria_api.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductDTO {
    private String name;
    private String code;
    private BigDecimal price;
    private String description;
    private Integer stock;
    private Long typeOfMeasureId;
    private Long categoriesId;
    private Long typeOfProductId;
}
