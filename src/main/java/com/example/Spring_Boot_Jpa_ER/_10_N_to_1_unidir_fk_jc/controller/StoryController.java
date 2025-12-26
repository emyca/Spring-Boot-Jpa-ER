package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.controller;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.StoryDtoRequest;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.StoryDtoResponse;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.service.StoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stories")
public class StoryController {

    private final StoryService service;

    public StoryController(
            @Qualifier("StoryServiceImpl")
            StoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StoryDtoResponse> createStory(
            @RequestBody StoryDtoRequest request) {
        StoryDtoResponse response = service.createStory(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<StoryDtoResponse> getAllStories() {
        StoryDtoResponse response = service.getAllStories();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryDtoResponse> getStoryById(
            @PathVariable("id") Long id) {
        StoryDtoResponse response = service.getStoryById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoryDtoResponse> updateStoryById(
            @PathVariable("id") Long id,
            @RequestBody StoryDtoRequest request) {
        StoryDtoResponse response = service.updateStoryById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StoryDtoResponse> deleteStoryById(
            @PathVariable(value = "id") Long id) {
        StoryDtoResponse response = service.deleteStoryById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
