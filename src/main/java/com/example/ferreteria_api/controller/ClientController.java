package com.example.ferreteria_api.controller;

import com.example.ferreteria_api.entity.Client;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.getClient();
    }

    @GetMapping("/{id}")
    public Optional<Client> getById(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Client client) {
        Client saveClient = clientService.save(client);
        ApiResponse response = new ApiResponse("successfully created", saveClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Client client) {
        client.setId(id);
        clientService.update(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        clientService.delete(id);
    }

}
