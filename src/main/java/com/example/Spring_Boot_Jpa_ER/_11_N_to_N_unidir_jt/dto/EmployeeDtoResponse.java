package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.model.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

public record EmployeeDtoResponse(
        int status,
        String reasonPhrase,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long projectId,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        EmployeeModel employee,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<EmployeeModel> employees) {

    private EmployeeDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.message,
                builder.projectId,
                builder.employee,
                builder.employees
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        String message;
        Long projectId;
        EmployeeModel employee;
        List<EmployeeModel> employees;

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

        public Builder projectId(Long projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder employee(EmployeeModel employee) {
            this.employee = employee;
            return this;
        }

        public Builder employees(List<EmployeeModel> employees) {
            this.employees = employees;
            return this;
        }

        public EmployeeDtoResponse build() {
            return new EmployeeDtoResponse(this);
        }
    }

    @Getter
    public enum Message {

        SUCCESS_SAVE_MSG("Employee has been saved successfully."),
        FAILURE_SAVE_MSG("Employee has not been saved."),
        SUCCESS_GET_LIST_MSG("Employees have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Employees have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Employee with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Employee with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Employee with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Employee with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

    }
}
