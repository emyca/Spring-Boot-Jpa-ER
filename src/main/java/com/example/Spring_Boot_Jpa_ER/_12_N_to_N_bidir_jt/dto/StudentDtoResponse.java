package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.model.StudentModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

public record StudentDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long courseId,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        StudentModel student,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<StudentModel> students) {

    private StudentDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.courseId,
                builder.student,
                builder.students
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        Long courseId;
        StudentModel student;
        List<StudentModel> students;

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

        public Builder courseId(Long courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder student(StudentModel student) {
            this.student = student;
            return this;
        }

        public Builder students(List<StudentModel> students) {
            this.students = students;
            return this;
        }

        public StudentDtoResponse build() {
            return new StudentDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_SAVE_MSG("Student has been saved successfully."),
        FAILURE_SAVE_MSG("Student has not been saved."),
        SUCCESS_GET_LIST_MSG("Students have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Students have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Student with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Student with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Student with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Student with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}
