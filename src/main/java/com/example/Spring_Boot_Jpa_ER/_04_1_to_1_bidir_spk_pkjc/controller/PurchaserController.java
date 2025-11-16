package com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.controller;

import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto.PurchaserDtoResponse;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.service.PurchaserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class PurchaserController {

    private final PurchaserService service;

    public PurchaserController(
            @Qualifier("PurchaserServiceImpl")
            PurchaserService service) {
        this.service = service;
    }

    @PostMapping("/purchasers")
    public ResponseEntity<PurchaserDtoResponse> createPurchaser(
            @RequestBody PurchaserDtoRequest request) {
        PurchaserDtoResponse response = service.createPurchaser(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/purchasers")
    public ResponseEntity<PurchaserDtoResponse> getAllPurchasers() {
        PurchaserDtoResponse response = service.getAllPurchasers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/habitations/purchasers")
    public ResponseEntity<PurchaserDtoResponse> getAllPurchasersByHabitation() {
        PurchaserDtoResponse response = service.getAllPurchasersByHabitation();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/purchasers/{id}")
    public ResponseEntity<PurchaserDtoResponse> getPurchaserById(
            @PathVariable("id") Long id) {
        PurchaserDtoResponse response = service.getPurchaserById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/purchasers/{id}")
    public ResponseEntity<PurchaserDtoResponse> updatePurchaserById(
            @PathVariable("id") Long id,
            @RequestBody PurchaserDtoRequest request) {
        PurchaserDtoResponse response = service.updatePurchaserById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/purchasers/{id}")
    public ResponseEntity<PurchaserDtoResponse> deletePurchaserById(
            @PathVariable(value = "id") Long id) {
        PurchaserDtoResponse response = service.deletePurchaserById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
