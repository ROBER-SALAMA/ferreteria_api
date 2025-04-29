package com.example.ferreteria_api.controller;

import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.global.GetResponse;
import com.example.ferreteria_api.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public ResponseEntity<GetResponse> getAll() {
        List<Roles> roles = rolesService.getRoles();

        if (roles.isEmpty()) {
            GetResponse response = new GetResponse(false, "No se encontraron roles activos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        GetResponse response = new GetResponse(true, "Roles found successfully");
        response.addField("roles", roles);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponse> getById(@PathVariable("id") Long id) {
            Roles role = rolesService.getRolesById(id);

            GetResponse response = new GetResponse(true, "Roles found successfully");
            response.addField("role", role);

            return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Roles roles) {
        Roles saveRoles = rolesService.save(roles);
        ApiResponse response = new ApiResponse("successfully created", saveRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Roles roles) {
        roles.setId(id);
        rolesService.update(roles);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        rolesService.delete(id);
        ApiResponse response = new ApiResponse("role successfully deleted", null);
        return ResponseEntity.ok(response);
    }

}
