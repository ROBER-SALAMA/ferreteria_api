package com.example.ferreteria_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name="TypeOfProduct")
public class TypeOfProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active",columnDefinition = "boolean default true")
    private boolean isActive = true;

    private String type;
}
