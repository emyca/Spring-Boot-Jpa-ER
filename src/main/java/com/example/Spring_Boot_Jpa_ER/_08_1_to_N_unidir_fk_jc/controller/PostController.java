package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.controller;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.PostDtoRequest;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.PostDtoResponse;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.service.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService service;

    public PostController(
            @Qualifier("PostServiceImpl")
            PostService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PostDtoResponse> createPost(
            @RequestBody PostDtoRequest request) {
        PostDtoResponse response = service.createPost(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<PostDtoResponse> getAllPosts() {
        PostDtoResponse response = service.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDtoResponse> getPostById(
            @PathVariable("id") Long id) {
        PostDtoResponse response = service.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDtoResponse> updatePostById(
            @PathVariable("id") Long id,
            @RequestBody PostDtoRequest request) {
        PostDtoResponse response = service.updatePostById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDtoResponse> deletePostById(
            @PathVariable(value = "id") Long id) {
        PostDtoResponse response = service.deletePostById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
