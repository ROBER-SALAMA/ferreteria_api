package com.example.ferreteria_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "JSON")
    private String jsonSales;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive = true;
}
