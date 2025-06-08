package com.example.ferreteria_api.service;

import com.example.ferreteria_api.entity.Categories;
import com.example.ferreteria_api.entity.Client;
import com.example.ferreteria_api.entity.Roles;
import com.example.ferreteria_api.global.CustomException;
import com.example.ferreteria_api.repository.ClientRepository;
import com.example.ferreteria_api.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<Client> getClient() {
        try {
            return clientRepository.findAll()
                    .stream()
                    .filter(Client::isActive) // Filtra solo los activos
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new CustomException(
                    "Error al procesar los Clientes: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public Client getClientById(Long id) {
        try {
            Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Client no encontrado con id: " + id, HttpStatus.NOT_FOUND));

            if (!client.isActive()) {
                throw new CustomException("Cliente con id " + id + " está inactivo", HttpStatus.NOT_FOUND);
            }

            return client;
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Internal server error: method findById from service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Client save(Client client) {
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method save from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    public void update(Client client) {
        try {
            if (client.getId() == null || !clientRepository.existsById(client.getId())) {
                throw new IllegalArgumentException("Failed to update: client does not exist.");
            }
            clientRepository.save(client);
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method update from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void delete(Long id) {
        try {
            Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("cliente no encontrado con id: " + id));

            if (client.isActive()) {
                client.setActive(false);
                clientRepository.save(client);
            }
        } catch (Exception e) {
            throw new CustomException(
                    "Internal server error: method delete from service",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}

