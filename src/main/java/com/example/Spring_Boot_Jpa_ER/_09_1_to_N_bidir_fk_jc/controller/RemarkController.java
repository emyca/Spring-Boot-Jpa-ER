package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.controller;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.RemarkDtoRequest;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.RemarkDtoResponse;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class RemarkController {

    @Autowired
    @Qualifier("RemarkServiceImpl")
    private RemarkService service;

    @PostMapping("/articles/{id}/remarks")
    public ResponseEntity<RemarkDtoResponse> createRemarkByArticleId(
            @PathVariable("id") Long id,
            @RequestBody RemarkDtoRequest request) {
        RemarkDtoResponse response =
                service.createRemarkByArticleId(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/articles/{id}/remarks")
    public ResponseEntity<RemarkDtoResponse> getAllRemarksByArticleId(
            @PathVariable("id") Long id) {
        RemarkDtoResponse response =
                service.getAllRemarksByArticleId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/remarks/{id}")
    public ResponseEntity<RemarkDtoResponse> getRemarkById(
            @PathVariable("id") Long id) {
        RemarkDtoResponse response =
                service.getRemarkById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/remarks/{id}")
    public ResponseEntity<RemarkDtoResponse> updateRemarkById(
            @PathVariable("id") Long id,
            @RequestBody RemarkDtoRequest request) {
        RemarkDtoResponse response =
                service.updateRemarkById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/remarks/{id}")
    public ResponseEntity<RemarkDtoResponse> deleteRemarkById(
            @PathVariable(value = "id") Long id) {
        RemarkDtoResponse response =
                service.deleteRemarkById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
