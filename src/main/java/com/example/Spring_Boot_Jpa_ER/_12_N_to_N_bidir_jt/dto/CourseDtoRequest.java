package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CourseDtoRequest(
        Long id,
        String name) {
}
