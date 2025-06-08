package com.example.ferreteria_api.repository;

import com.example.ferreteria_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
