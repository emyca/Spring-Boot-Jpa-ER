package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.model.PostModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

public record PostDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        PostModel post,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<PostModel> posts) {

    private PostDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.post,
                builder.posts
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        PostModel post;
        List<PostModel> posts;

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

        public Builder post(PostModel post) {
            this.post = post;
            return this;
        }

        public Builder posts(List<PostModel> posts) {
            this.posts = posts;
            return this;
        }

        public PostDtoResponse build() {
            return new PostDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_CREATE_MSG("Post has been created successfully."),
        FAILURE_CREATE_MSG("Post has not been created."),
        SUCCESS_GET_LIST_MSG("Posts have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Posts have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Post with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Post with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Post with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Post with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}

