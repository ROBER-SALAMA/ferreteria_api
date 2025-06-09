package com.example.ferreteria_api.service;

import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.entity.Sales;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.repository.RolesRepository;
import com.example.ferreteria_api.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SalesService {

    //injeccion de dependencias
    @Autowired
    SalesRepository salesRepository;

    public List<Sales> getSales() {
        try {
            return salesRepository.findAll()
                    .stream()
                    .filter(Sales::isActive) // Filtra solo los activos
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new CustomException(
                    "Error al procesar Sales: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public Sales getSalesById(Long id) {
        try {
            Sales sales = salesRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Rol no encontrado con id: " + id, HttpStatus.NOT_FOUND));

            if (!sales.isActive()) {
                throw new CustomException("sales con id " + id + " está inactivo", HttpStatus.NOT_FOUND);
            }

            return sales;
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Internal server error: method findById from service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public Sales save(Sales sales) {
        try {
            return salesRepository.save(sales);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method save from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }



    public void delete(Long id) {
       try {
           Sales sales = salesRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("sales no encontrado con id: " + id));

           if (sales.isActive()) {
               sales.setActive(false);
               salesRepository.save(sales);
           }
       } catch (Exception e) {
           throw new CustomException(
                   "Internal server error: method delete from service",
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
       }
    }
}
