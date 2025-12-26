package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.controller;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.OpinionDtoRequest;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.OpinionDtoResponse;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.service.OpinionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class OpinionController {

    private final OpinionService service;

    public OpinionController(
            @Qualifier("OpinionServiceImpl")
            OpinionService service) {
        this.service = service;
    }

    @PostMapping("/stories/{id}/opinions")
    public ResponseEntity<OpinionDtoResponse> createOpinionByStoryId(
            @PathVariable("id") Long id,
            @RequestBody OpinionDtoRequest request) {
        OpinionDtoResponse response = service.createOpinionByStoryId(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/stories/{id}/opinions")
    public ResponseEntity<OpinionDtoResponse> getAllOpinionsByStoryId(
            @PathVariable("id") Long id) {
        OpinionDtoResponse response = service.getAllOpinionsByStoryId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/opinions/{id}")
    public ResponseEntity<OpinionDtoResponse> getOpinionById(
            @PathVariable("id") Long id) {
        OpinionDtoResponse response = service.getOpinionById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/opinions/{id}")
    public ResponseEntity<OpinionDtoResponse> updateOpinionById(
            @PathVariable("id") Long id,
            @RequestBody OpinionDtoRequest request) {
        OpinionDtoResponse response = service.updateOpinionById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/opinions/{id}")
    public ResponseEntity<OpinionDtoResponse> deleteOpinionById(
            @PathVariable(value = "id") Long id) {
        OpinionDtoResponse response = service.deleteOpinionById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
