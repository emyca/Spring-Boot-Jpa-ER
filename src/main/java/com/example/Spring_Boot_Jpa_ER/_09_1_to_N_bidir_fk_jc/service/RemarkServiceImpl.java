package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.RemarkDtoRequest;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.RemarkDtoResponse;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.entity.Article;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.entity.Remark;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.model.RemarkModel;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.repository.ArticleRepository;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.repository.RemarkRepository;
import com.example.Spring_Boot_Jpa_ER.common.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("RemarkServiceImpl")
public class RemarkServiceImpl implements RemarkService {

    private final ArticleRepository articleRepository;
    private final RemarkRepository remarkRepository;

    public RemarkServiceImpl(
            ArticleRepository articleRepository,
            RemarkRepository remarkRepository) {
        this.articleRepository = articleRepository;
        this.remarkRepository = remarkRepository;
    }

    @Override
    public RemarkDtoResponse createRemarkByArticleId(
            Long id, RemarkDtoRequest request)
            throws ResourceException {
        try {
            return articleRepository.findById(id).map(article -> {
                Remark remark = new Remark();
                remark.setConsideration(request.consideration());
                remark.setArticle(article);
                article.getRemarks().add(remark);
                return new RemarkDtoResponse.Builder()
                        .status(HttpStatus.OK.value())
                        .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                        .message(RemarkDtoResponse
                                .Message.SUCCESS_CREATE_MSG.getMessage())
                        .articleId(id)
                        .remark(RemarkModel.getModel(
                                remarkRepository.save(remark)))
                        .build();
            }).orElseThrow(() ->
                    new ResourceException(
                            "Article with id %s has not been found!"
                                    .formatted(id)));
        } catch (ResourceException e) {
            return new RemarkDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public RemarkDtoResponse getAllRemarksByArticleId(Long id) {
        try {
            Article article = articleRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Article with id %s has not been found!"
                                            .formatted(id)));
            List<Remark> list = new ArrayList<>(article.getRemarks());
            return (!list.isEmpty())
                    ? new RemarkDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(RemarkDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .articleId(id)
                    .remarks(RemarkModel.getModel(list))
                    .build()
                    : new RemarkDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(RemarkDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .articleId(id)
                    .remarks(Collections.emptyList())
                    .build();
        } catch (ResourceException e) {
            return new RemarkDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public RemarkDtoResponse getRemarkById(Long id) {
        try {
            Remark remark = remarkRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Remark with id %s has not been found!"
                                            .formatted(id)));
            return new RemarkDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(RemarkDtoResponse
                            .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .articleId(RemarkModel.getArticleId(remark))
                    .remark(RemarkModel.getModel(remark))
                    .build();
        } catch (ResourceException e) {
            return new RemarkDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public RemarkDtoResponse updateRemarkById(Long id, RemarkDtoRequest request) {
        try {
            Remark remark = remarkRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Remark with id %s has not been found!"
                                            .formatted(id)));
            remark.setConsideration(request.consideration());
            Remark _remark = remarkRepository.save(remark);
            return new RemarkDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(RemarkDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .articleId(RemarkModel.getArticleId(_remark))
                    .remark(RemarkModel.getModel(_remark))
                    .build();
        } catch (ResourceException e) {
            return new RemarkDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public RemarkDtoResponse deleteRemarkById(Long id) {
        if (remarkRepository.findById(id).isPresent()) {
            remarkRepository.deleteById(id);
            return new RemarkDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(RemarkDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new RemarkDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(RemarkDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
