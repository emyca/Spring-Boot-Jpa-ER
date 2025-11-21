package com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto;

import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.model.ConsumerModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

public record ConsumerDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        ConsumerModel consumer,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<ConsumerModel> consumerList) {

    private ConsumerDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.consumer,
                builder.consumerList
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        ConsumerModel consumer;
        List<ConsumerModel> consumerList;

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

        public Builder consumer(ConsumerModel consumer) {
            this.consumer = consumer;
            return this;
        }

        public Builder consumerList(List<ConsumerModel> consumerList) {
            this.consumerList = consumerList;
            return this;
        }

        public ConsumerDtoResponse build() {
            return new ConsumerDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_CREATE_MSG("Consumer has been created successfully."),
        FAILURE_CREATE_MSG("Consumer has been not created!"),
        SUCCESS_GET_LIST_MSG("Consumer list has been fetched successfully."),
        FAILURE_GET_LIST_MSG("Consumer list has not been found!"),
        SUCCESS_GET_BY_ID_MSG("Consumer with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Consumer with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Consumer with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Consumer with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}
