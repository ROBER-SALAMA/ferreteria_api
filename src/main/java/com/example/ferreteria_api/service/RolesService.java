package com.example.ferreteria_api.service;

import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RolesService {

    //injeccion de dependencias
    @Autowired
    RolesRepository rolesRepository;

    public List<Roles> getRoles() {
        try {
            return rolesRepository.findAll()
                    .stream()
                    .filter(Roles::isActive) // Filtra solo los activos
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new CustomException(
                    "Error al procesar los roles: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public Optional<Roles> getRolesById(Long id){
        try {
             rolesRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method findById from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return Optional.empty();
    }

    public Roles save(Roles roles) {
        try {
            return rolesRepository.save(roles);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method save from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    public void update(Roles roles) {
        try {
            if (roles.getId() == null || !rolesRepository.existsById(roles.getId())) {
                throw new IllegalArgumentException("Failed to update: role does not exist.");
            }
            rolesRepository.save(roles);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method update from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void delete(Long id) {
       try {
           Roles role = rolesRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));

           if (role.isActive()) {
               role.setActive(false);
               rolesRepository.save(role);
           }
       } catch (Exception e) {
           throw new CustomException(
                   "Internal server error: method delete from service",
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
       }
    }
}
