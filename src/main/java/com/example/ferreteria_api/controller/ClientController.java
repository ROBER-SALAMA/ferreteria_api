package com.example.ferreteria_api.controller;

import com.example.ferreteria_api.entity.Client;
import com.example.ferreteria_api.global.ApiResponse;
import com.example.ferreteria_api.global.GetResponse;
import com.example.ferreteria_api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<GetResponse> getAll() {
        List<Client> client = clientService.getClient();

        if (client.isEmpty()) {
            GetResponse response = new GetResponse(false, "No se encontraron clientes activos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        GetResponse response = new GetResponse(true, "Clients found successfully");
        response.addField("clients", client);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponse> getById(@PathVariable("id") Long id) {
        Client client = clientService.getClientById(id);

        GetResponse response = new GetResponse(true, "Clients found successfully");
        response.addField("client", client);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Client client ) {
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
