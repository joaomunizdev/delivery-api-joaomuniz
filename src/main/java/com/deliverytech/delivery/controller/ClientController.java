package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.entity.Client;
import com.deliverytech.delivery.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client novoCliente = clientService.create(client);
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> findActiveClients() {
        return ResponseEntity.ok(clientService.findActiveClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findClientById(@PathVariable Integer id) {
        try {
            Client client = clientService.findById(id);
            return ResponseEntity.ok(client);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        try {
            Client updatedClient = clientService.update(id, client);
            return ResponseEntity.ok(updatedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inactivateClient(@PathVariable Integer id) {
        try {
            clientService.inactivate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}