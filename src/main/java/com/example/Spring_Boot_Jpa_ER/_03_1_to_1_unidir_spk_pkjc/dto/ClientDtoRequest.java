package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ClientDtoRequest(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        String street,
        String building,
        String apartment) {
}
