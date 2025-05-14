package com.example.ferreteria_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;
    @Column(name = "isActive",columnDefinition = "boolean default true")
    private boolean isActive = true;
}
