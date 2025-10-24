package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerDtoRequest(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        String street,
        String building,
        String apartment) {
}
