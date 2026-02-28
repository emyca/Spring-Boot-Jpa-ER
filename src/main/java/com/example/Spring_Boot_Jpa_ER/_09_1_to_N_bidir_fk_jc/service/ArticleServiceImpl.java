package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.ArticleDtoRequest;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.ArticleDtoResponse;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.entity.Article;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.mapper.ArticleMapper;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.model.ArticleModel;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.repository.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("ArticleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(
            ArticleMapper articleMapper,
            ArticleRepository articleRepository) {
        this.articleMapper = articleMapper;
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDtoResponse createArticle(ArticleDtoRequest request) {
        Article article = articleRepository.save(articleMapper
                .dtoCreateToEntity(request));
        return (article != null)
                ? new ArticleDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(ArticleDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .article(ArticleModel.getModel(article))
                .build()
                : new ArticleDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(ArticleDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public ArticleDtoResponse getAllArticles() {
        List<Article> list = articleRepository.findAll();
        if (!list.isEmpty()) {
            List<ArticleModel> _list = new ArrayList<>();
            for (Article article : list)
                _list.add(ArticleModel.getModel(article));
            return new ArticleDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ArticleDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .articles(_list)
                    .build();
        } else
            return new ArticleDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(ArticleDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .articles(Collections.emptyList())
                    .build();
    }

    @Override
    public ArticleDtoResponse getArticleById(Long id) {
        Optional<Article> optional = articleRepository.findById(id);
        return (optional.isPresent())
                ? new ArticleDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(ArticleDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .article(ArticleModel.getModel(optional.get()))
                .build()
                : new ArticleDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ArticleDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public ArticleDtoResponse updateArticleById(Long id, ArticleDtoRequest request) {
        Optional<Article> optional = articleRepository.findById(id)
                .map(article ->
                        articleRepository.saveAndFlush(
                                articleMapper.dtoUpdateByIdToEntity(
                                        id, request, article)));
        return (optional.isPresent())
                ? new ArticleDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(ArticleDtoResponse
                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                        .formatted(id))
                .article(ArticleModel.getModel(optional.get()))
                .build()
                : new ArticleDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ArticleDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public ArticleDtoResponse deleteArticleById(Long id) {
        if (articleRepository.findById(id).isPresent()) {
            articleRepository.deleteById(id);
            return new ArticleDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ArticleDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new ArticleDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ArticleDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
