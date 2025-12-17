package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.model.ArticleModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

public record ArticleDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        ArticleModel article,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<ArticleModel> articles) {

    private ArticleDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.article,
                builder.articles
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        ArticleModel article;
        List<ArticleModel> articles;

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder reasonPhrase(String reasonPhrase) {
            this.reasonPhrase = reasonPhrase;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder article(ArticleModel article) {
            this.article = article;
            return this;
        }

        public Builder articles(List<ArticleModel> articles) {
            this.articles = articles;
            return this;
        }

        public ArticleDtoResponse build() {
            return new ArticleDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_CREATE_MSG("Article has been created successfully."),
        FAILURE_CREATE_MSG("Article has not been created."),
        SUCCESS_GET_LIST_MSG("Articles have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Articles have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Article with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Article with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Article with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Article with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}
