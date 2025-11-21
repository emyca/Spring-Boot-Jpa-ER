package com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.controller;

import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoResponse;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.service.ConsumerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/consumers")
public class ConsumerController {

    private final ConsumerService service;

    public ConsumerController(
            @Qualifier("ConsumerServiceImpl")
            ConsumerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ConsumerDtoResponse> createConsumer(
            @RequestBody ConsumerDtoRequest request) {
        ConsumerDtoResponse response = service.createConsumer(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ConsumerDtoResponse> getAllConsumers() {
        ConsumerDtoResponse response = service.getAllConsumers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumerDtoResponse> getConsumerById(
            @PathVariable("id") Long id) {
        ConsumerDtoResponse response = service.getConsumerById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumerDtoResponse> updateConsumerById(
            @PathVariable("id") Long id,
            @RequestBody ConsumerDtoRequest request) {
        ConsumerDtoResponse response = service.updateConsumerById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsumerDtoResponse> deleteConsumerById(
            @PathVariable(value = "id") Long id) {
        ConsumerDtoResponse response = service.deleteConsumerById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
