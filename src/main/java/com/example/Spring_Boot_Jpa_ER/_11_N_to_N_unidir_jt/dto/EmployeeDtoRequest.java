package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmployeeDtoRequest(
        Long id,
        String firstName,
        String lastName,
        String email) {
}
