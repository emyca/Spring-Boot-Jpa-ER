package com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto;

import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.model.PurchaserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

import java.util.List;

public record PurchaserDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(Include.NON_NULL)
        PurchaserModel purchaser,
        @JsonInclude(Include.NON_NULL)
        List<PurchaserModel> purchaserList) {

    private PurchaserDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.purchaser,
                builder.purchaserList
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        PurchaserModel purchaser;
        List<PurchaserModel> purchaserList;

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

        public Builder purchaser(PurchaserModel purchaser) {
            this.purchaser = purchaser;
            return this;
        }

        public Builder purchaserList(List<PurchaserModel> purchaserList) {
            this.purchaserList = purchaserList;
            return this;
        }

        public PurchaserDtoResponse build() {
            return new PurchaserDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_CREATE_MSG("Purchaser has been created successfully."),
        FAILURE_CREATE_MSG("Purchaser has not been created."),
        SUCCESS_GET_LIST_MSG("Purchaser list has been fetched successfully."),
        FAILURE_GET_LIST_MSG("Purchaser list has not been found!"),
        SUCCESS_GET_BY_ID_MSG("Purchaser with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Purchaser with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Purchaser with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Purchaser with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}
