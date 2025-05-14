package com.example.ferreteria_api.repository;

import com.example.ferreteria_api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client ,Long> {
}
