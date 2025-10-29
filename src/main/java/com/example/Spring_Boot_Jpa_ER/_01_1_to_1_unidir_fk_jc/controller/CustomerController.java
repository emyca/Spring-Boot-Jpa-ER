package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.controller;

import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoResponse;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    @Qualifier("CustomerServiceImpl")
    private CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDtoResponse> createCustomer(
            @RequestBody CustomerDtoRequest request) {
        CustomerDtoResponse response = service.createCustomer(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CustomerDtoResponse> getAllCustomers() {
        CustomerDtoResponse response = service.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> getCustomerById(
            @PathVariable("id") Long id) {
        CustomerDtoResponse response = service.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> updateCustomerById(
            @PathVariable("id") Long id,
            @RequestBody CustomerDtoRequest request) {
        CustomerDtoResponse response = service.updateCustomerById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> deleteCustomerById(
            @PathVariable(value = "id") Long id) {
        CustomerDtoResponse response = (service.deleteCustomerById(id));
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
