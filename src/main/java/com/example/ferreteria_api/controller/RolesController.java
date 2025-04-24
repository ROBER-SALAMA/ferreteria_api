package com.example.ferreteria_api.controller;

import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public List<Roles> getAll() {
        return rolesService.getRoles();
    }

    @GetMapping("/{id}")
    public Optional<Roles> getById(@PathVariable("id") Long id) {
        return rolesService.getRolesById(id);
    }

    @PostMapping
    public void save(@RequestBody Roles roles) {
        rolesService.saveOrUpdate(roles);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        rolesService.delete(id);
    }

}
