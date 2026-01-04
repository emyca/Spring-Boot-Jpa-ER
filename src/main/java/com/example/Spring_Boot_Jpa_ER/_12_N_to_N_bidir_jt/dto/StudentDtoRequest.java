package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StudentDtoRequest(
        Long id,
        String firstName,
        String lastName,
        String email) {
}
