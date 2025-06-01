package com.example.ferreteria_api.service;

import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.entity.TypeOfMeasure;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.repository.TypeOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TypeOfMeasureService {
    @Autowired
    TypeOfMeasureRepository typeOfMeasureRepository;

    public List<TypeOfMeasure>getTypeOfMeasure(){
        try{
            return typeOfMeasureRepository.findAll()
                    .stream()
                    .filter(TypeOfMeasure::isActive)
                    .collect(Collectors.toUnmodifiableList());
        }catch (Exception ex){
            throw new CustomException(
                    "Error al procesar los tipos de medidas: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public TypeOfMeasure getTypeOfMeasureById(Long id) {
        try {
            TypeOfMeasure typeOfMeasure = typeOfMeasureRepository.findById(id)
                    .orElseThrow(() -> new CustomException("typeOfMeasure no encontrado con id: " + id, HttpStatus.NOT_FOUND));

            if (!typeOfMeasure.isActive()) {
                throw new CustomException("typeOfMeasure con id " + id + " está inactivo", HttpStatus.NOT_FOUND);
            }

            return typeOfMeasure;
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Internal server error: method findById from service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public TypeOfMeasure save(TypeOfMeasure typeOfMeasure){
        try {
            return typeOfMeasureRepository.save(typeOfMeasure);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method save from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void update(TypeOfMeasure typeOfMeasure){
        try {
            if (typeOfMeasure.getId()== null || !typeOfMeasureRepository.existsById(typeOfMeasure.getId())) {
                throw new IllegalArgumentException("Faild update: Type of measure does not exist");
            }
            typeOfMeasureRepository.save(typeOfMeasure);
        } catch (Exception e){
            throw new CustomException(
                    "Internal server error: method update from service ",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void delete(Long id ){
        try {
            TypeOfMeasure typeOfMeasure = typeOfMeasureRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
            if (typeOfMeasure.isActive()){
                typeOfMeasure.setActive(false);
                typeOfMeasureRepository.save(typeOfMeasure);
            }
        }catch (Exception e){
            throw new CustomException(
                    "Internal server error: method delete from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
