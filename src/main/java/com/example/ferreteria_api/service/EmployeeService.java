package com.example.ferreteria_api.service;

import com.example.ferreteria_api.entity.Client;
import com.example.ferreteria_api.entity.Employee;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.repository.ClientRepository;
import com.example.ferreteria_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployee() {
        try {
            return employeeRepository.findAll()
                    .stream()
                    .filter(Employee::isActive) // Filtra solo los activos
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new CustomException(
                    "Error al procesar los Employee: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public Optional<Employee> getEmployeeById(Long id){
        try {
            return employeeRepository.findById(id);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method findById from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    public Employee save(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method save from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    public void update(Employee employee) {
        try {
            if (employee.getId() == null || !employeeRepository.existsById(employee.getId())) {
                throw new IllegalArgumentException("Failed to update: employeeRepository does not exist.");
            }
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method update from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void delete(Long id) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("employee no encontrado con id: " + id));

            if (employee.isActive()) {
                employee.setActive(false);
                employeeRepository.save(employee);
            }
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method delete from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}

