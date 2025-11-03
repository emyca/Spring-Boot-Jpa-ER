package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto;

import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.model.ClientModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

public record ClientDtoResponse(
        int status,
        String reasonPhrase,
        boolean success,
        String message,
        @JsonInclude(Include.NON_NULL)
        ClientModel client,
        @JsonInclude(Include.NON_NULL)
        List<ClientModel> clientList) {

    private ClientDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.success,
                builder.message,
                builder.client,
                builder.clientList
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        boolean success;
        String message;
        ClientModel client;
        List<ClientModel> clientList;

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder reasonPhrase(String reasonPhrase) {
            this.reasonPhrase = reasonPhrase;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder client(ClientModel client) {
            this.client = client;
            return this;
        }

        public Builder clientList(List<ClientModel> clientList) {
            this.clientList = clientList;
            return this;
        }

        public ClientDtoResponse build() {
            return new ClientDtoResponse(this);
        }
    }

    public enum Message {

        SUCCESS_CREATE_MSG("Client has been created successfully."),
        FAILURE_CREATE_MSG("Client has not been created!"),
        SUCCESS_GET_LIST_MSG("Client list has been fetched successfully."),
        FAILURE_GET_LIST_MSG("Client list has not been found!"),
        SUCCESS_GET_BY_ID_MSG("Client with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Client with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Client with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Client with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
