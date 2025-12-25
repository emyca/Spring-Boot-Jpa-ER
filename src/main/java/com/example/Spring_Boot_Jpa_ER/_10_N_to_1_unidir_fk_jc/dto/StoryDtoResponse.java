package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.model.StoryModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

public record StoryDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        StoryModel story,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<StoryModel> stories) {

    private StoryDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.story,
                builder.stories
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        StoryModel story;
        List<StoryModel> stories;

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

        public Builder story(StoryModel story) {
            this.story = story;
            return this;
        }

        public Builder stories(List<StoryModel> stories) {
            this.stories = stories;
            return this;
        }

        public StoryDtoResponse build() {
            return new StoryDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_CREATE_MSG("Story has been created successfully."),
        FAILURE_CREATE_MSG("Story has not been created."),
        SUCCESS_GET_LIST_MSG("Stories have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Stories have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Story with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Story with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Story with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Story with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}

