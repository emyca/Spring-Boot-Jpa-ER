package com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.controller;

import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto.ShopperDtoResponse;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ShopperController {

    @Autowired
    @Qualifier("ShopperServiceImpl")
    private ShopperService service;

    @PostMapping("/shoppers")
    public ResponseEntity<ShopperDtoResponse> createShopper(
            @RequestBody ShopperDtoRequest request) {
        ShopperDtoResponse response = service.createShopper(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/shoppers")
    public ResponseEntity<ShopperDtoResponse> getAllShoppers() {
        ShopperDtoResponse response = service.getAllShoppers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/places/shoppers")
    public ResponseEntity<ShopperDtoResponse> getAllShoppersByPlace() {
        ShopperDtoResponse response = service.getAllShoppersByPlace();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/shoppers/{id}")
    public ResponseEntity<ShopperDtoResponse> getShopperById(
            @PathVariable("id") Long id) {
        ShopperDtoResponse response = service.getShopperById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/shoppers/{id}")
    public ResponseEntity<ShopperDtoResponse> updateShopperById(
            @PathVariable("id") Long id,
            @RequestBody ShopperDtoRequest request) {
        ShopperDtoResponse response = service.updateShopperById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/shoppers/{id}")
    public ResponseEntity<ShopperDtoResponse> deleteShopperById(
            @PathVariable(value = "id") Long id) {
        ShopperDtoResponse response = service.deleteShopperById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
