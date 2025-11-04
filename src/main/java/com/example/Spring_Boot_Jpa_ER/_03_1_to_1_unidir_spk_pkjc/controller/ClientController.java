package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.controller;

import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoRequest;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoResponse;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    @Autowired
    @Qualifier("ClientServiceImpl")
    private ClientService service;

    @PostMapping
    public ResponseEntity<ClientDtoResponse> createClient(
            @RequestBody ClientDtoRequest request) {
        ClientDtoResponse response = service.createClient(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ClientDtoResponse> getAllClients() {
        ClientDtoResponse response = service.getAllClients();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDtoResponse> getClientById(
            @PathVariable("id") Long id) {
        ClientDtoResponse response = service.getClientById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDtoResponse> updateClientById(
            @PathVariable("id") Long id,
            @RequestBody ClientDtoRequest request) {
        ClientDtoResponse response = service.updateClientById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDtoResponse> deleteClientById(
            @PathVariable(value = "id") Long id) {
        ClientDtoResponse response = service.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
