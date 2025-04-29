package com.example.ferreteria_api.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "type_of_measure")
public class TypeOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive = true;
}
