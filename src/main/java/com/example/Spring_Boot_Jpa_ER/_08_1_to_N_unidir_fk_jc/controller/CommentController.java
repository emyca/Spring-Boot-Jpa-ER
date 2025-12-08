package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.controller;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.CommentDtoRequest;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.CommentDtoResponse;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.service.CommentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class CommentController {

    private final CommentService service;

    public CommentController(
            @Qualifier("CommentServiceImpl")
            CommentService service) {
        this.service = service;
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CommentDtoResponse> createCommentByPostId(
            @PathVariable("id") Long id,
            @RequestBody CommentDtoRequest request) {
        CommentDtoResponse response = service.createCommentByPostId(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<CommentDtoResponse> getAllCommentsByPostId(
            @PathVariable("id") Long id) {
        CommentDtoResponse response = service.getAllCommentsByPostId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDtoResponse> getCommentById(
            @PathVariable("id") Long id) {
        CommentDtoResponse response = service.getCommentById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentDtoResponse> updateCommentById(
            @PathVariable("id") Long id,
            @RequestBody CommentDtoRequest request) {
        CommentDtoResponse response = service.updateCommentById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDtoResponse> deleteCommentById(
            @PathVariable(value = "id") Long id) {
        CommentDtoResponse response = service.deleteCommentById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
