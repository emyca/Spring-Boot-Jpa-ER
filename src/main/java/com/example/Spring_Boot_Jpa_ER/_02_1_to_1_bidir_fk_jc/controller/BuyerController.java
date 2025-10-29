package com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.controller;

import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoResponse;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class BuyerController {

    @Autowired
    @Qualifier("BuyerServiceImpl")
    private BuyerService service;

    @PostMapping("/buyers")
    public ResponseEntity<BuyerDtoResponse> createBuyer(
            @RequestBody BuyerDtoRequest request) {
        BuyerDtoResponse response = service.createBuyer(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/buyers")
    public ResponseEntity<BuyerDtoResponse> getAllBuyers() {
        BuyerDtoResponse response = service.getAllBuyers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/addresses/buyers")
    public ResponseEntity<BuyerDtoResponse> getAllBuyersByAddress() {
        BuyerDtoResponse response = service.getAllBuyersByAddress();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/buyers/{id}")
    public ResponseEntity<BuyerDtoResponse> getBuyerById(
            @PathVariable("id") Long id) {
        BuyerDtoResponse response = service.getBuyerById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/buyers/{id}")
    public ResponseEntity<BuyerDtoResponse> updateBuyerById(
            @PathVariable("id") Long id,
            @RequestBody BuyerDtoRequest request) {
        BuyerDtoResponse response = service.updateBuyerById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/buyers/{id}")
    public ResponseEntity<BuyerDtoResponse> deleteBuyerById(
            @PathVariable(value = "id") Long id) {
        BuyerDtoResponse response = service.deleteBuyerById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
