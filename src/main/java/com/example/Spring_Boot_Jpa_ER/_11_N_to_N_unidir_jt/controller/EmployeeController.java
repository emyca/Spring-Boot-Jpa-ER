package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.controller;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.EmployeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.EmployeeDtoResponse;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(
            @Qualifier("EmployeeServiceImpl")
            EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDtoResponse> createEmployee(
            @RequestBody EmployeeDtoRequest request) {
        EmployeeDtoResponse response = service.createEmployee(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/employees")
    public ResponseEntity<EmployeeDtoResponse> getAllEmployees() {
        EmployeeDtoResponse response = service.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDtoResponse> getEmployeeById(
            @PathVariable("id") Long id) {
        EmployeeDtoResponse response = service.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/projects/{id}/employees")
    public ResponseEntity<EmployeeDtoResponse> getAllEmployeesByProjectId(
            @PathVariable("id") Long id) {
        EmployeeDtoResponse response = service.getAllEmployeesByProjectId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDtoResponse> updateEmployeeById(
            @PathVariable("id") Long id,
            @RequestBody EmployeeDtoRequest request) {
        EmployeeDtoResponse response = service.updateEmployeeById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<EmployeeDtoResponse> deleteEmployeeById(
            @PathVariable(value = "id") Long id) {
        EmployeeDtoResponse response = service.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
