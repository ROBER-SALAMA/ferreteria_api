package com.example.ferreteria_api.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String dui;
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive = true;
}
