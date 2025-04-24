package com.example.ferreteria_api.service;

import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RolesService {

    //injeccion de dependencias
    @Autowired
    RolesRepository rolesRepository;

    public List<Roles> getRoles() {
        return  rolesRepository.findAll();
    }

    public Optional<Roles> getRolesById(Long id){
        return rolesRepository.findById(id);
    }

    public void saveOrUpdate(Roles roles) {
        rolesRepository.save(roles);
    }

    public void delete(Long id) {
        rolesRepository.deleteById(id);
    }
}
