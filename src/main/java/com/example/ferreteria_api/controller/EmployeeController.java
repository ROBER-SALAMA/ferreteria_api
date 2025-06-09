package com.example.ferreteria_api.controller;


import com.example.ferreteria_api.entity.Client;
import com.example.ferreteria_api.entity.Employee;
import com.example.ferreteria_api.entity.Product;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.global.GetResponse;
import com.example.ferreteria_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<GetResponse> getAll() {
        List<Employee> employees = employeeService.getEmployee();

        if (employees.isEmpty()) {
            GetResponse response = new GetResponse(false, "No se encontraron empleados activos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        GetResponse response = new GetResponse(true, "Employees found successfully");
        response.addField("employees", employees);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Employee employee) {
        Employee saveEmployee = employeeService.save(employee);
        ApiResponse response = new ApiResponse("successfully created", saveEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

}
