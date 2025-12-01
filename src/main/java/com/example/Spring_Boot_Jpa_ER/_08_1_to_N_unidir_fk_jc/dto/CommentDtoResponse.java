package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.model.CommentModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

public record CommentDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long postId,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        CommentModel comment,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<CommentModel> comments) {

    private CommentDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.postId,
                builder.comment,
                builder.comments
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        Long postId;
        CommentModel comment;
        List<CommentModel> comments;

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

        public Builder postId(Long postId) {
            this.postId = postId;
            return this;
        }

        public Builder comment(CommentModel comment) {
            this.comment = comment;
            return this;
        }

        public Builder comments(List<CommentModel> comments) {
            this.comments = comments;
            return this;
        }

        public CommentDtoResponse build() {
            return new CommentDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_CREATE_MSG("Comment has been created successfully."),
        FAILURE_CREATE_MSG("Comment has not been created."),
        SUCCESS_GET_LIST_MSG("Comments have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Comments have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Comment with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Comment with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Comment with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Comment with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}
