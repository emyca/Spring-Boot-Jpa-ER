package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.controller;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoResponse;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.service.VendeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class VendeeController {

    private final VendeeService service;

    public VendeeController(
            @Qualifier("VendeeServiceImpl")
            VendeeService service) {
        this.service = service;
    }

    @PostMapping("/vendees")
    public ResponseEntity<VendeeDtoResponse> createVendee(
            @RequestBody VendeeDtoRequest request) {
        VendeeDtoResponse response = service.createVendee(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/vendees")
    public ResponseEntity<VendeeDtoResponse> getAllVendees() {
        VendeeDtoResponse response = service.getAllVendees();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/abodes/vendees")
    public ResponseEntity<VendeeDtoResponse> getAllVendeesByAbode() {
        VendeeDtoResponse response = service.getAllVendeesByAbode();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/vendees/{id}")
    public ResponseEntity<VendeeDtoResponse> getVendeeById(
            @PathVariable("id") Long id) {
        VendeeDtoResponse response = service.getVendeeById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/vendees/{id}")
    public ResponseEntity<VendeeDtoResponse> updateVendeeById(
            @PathVariable("id") Long id,
            @RequestBody VendeeDtoRequest request) {
        VendeeDtoResponse response = service.updateVendeeById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/vendees/{id}")
    public ResponseEntity<VendeeDtoResponse> deleteVendeeById(
            @PathVariable(value = "id") Long id) {
        VendeeDtoResponse response = service.deleteVendeeById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
