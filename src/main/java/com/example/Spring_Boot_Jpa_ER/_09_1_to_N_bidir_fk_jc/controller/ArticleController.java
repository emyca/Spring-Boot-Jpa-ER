package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.controller;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.ArticleDtoRequest;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.ArticleDtoResponse;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.service.ArticleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/articles")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(
            @Qualifier("ArticleServiceImpl")
            ArticleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ArticleDtoResponse> createArticle(
            @RequestBody ArticleDtoRequest request) {
        ArticleDtoResponse response = service.createArticle(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ArticleDtoResponse> getAllArticles() {
        ArticleDtoResponse response = service.getAllArticles();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDtoResponse> getArticleById(
            @PathVariable("id") Long id) {
        ArticleDtoResponse response = service.getArticleById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDtoResponse> updateArticleById(
            @PathVariable("id") Long id,
            @RequestBody ArticleDtoRequest request) {
        ArticleDtoResponse response = service.updateArticleById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleDtoResponse> deleteArticleById(
            @PathVariable(value = "id") Long id) {
        ArticleDtoResponse response = service.deleteArticleById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
