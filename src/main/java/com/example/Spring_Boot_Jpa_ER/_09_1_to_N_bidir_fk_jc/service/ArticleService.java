package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.ArticleDtoRequest;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.ArticleDtoResponse;

public interface ArticleService {
    ArticleDtoResponse createArticle(ArticleDtoRequest request);
    ArticleDtoResponse getAllArticles();
    ArticleDtoResponse getArticleById(Long id);
    ArticleDtoResponse updateArticleById(Long id, ArticleDtoRequest request);
    ArticleDtoResponse deleteArticleById(Long id);
}
