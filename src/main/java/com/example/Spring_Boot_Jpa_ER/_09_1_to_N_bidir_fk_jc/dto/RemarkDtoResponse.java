package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.model.RemarkModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

public record RemarkDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long articleId,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        RemarkModel remark,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<RemarkModel> remarks) {

    private RemarkDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.articleId,
                builder.remark,
                builder.remarks
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        Long articleId;
        RemarkModel remark;
        List<RemarkModel> remarks;

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

        public Builder articleId(Long articleId) {
            this.articleId = articleId;
            return this;
        }

        public Builder remark(RemarkModel remark) {
            this.remark = remark;
            return this;
        }

        public Builder remarks(List<RemarkModel> remarks) {
            this.remarks = remarks;
            return this;
        }

        public RemarkDtoResponse build() {
            return new RemarkDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_CREATE_MSG("Remark has been created successfully."),
        FAILURE_CREATE_MSG("Remark has not been created."),
        SUCCESS_GET_LIST_MSG("Remarks have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Remarks have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Remark with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Remark with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Remark with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Remark with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}
