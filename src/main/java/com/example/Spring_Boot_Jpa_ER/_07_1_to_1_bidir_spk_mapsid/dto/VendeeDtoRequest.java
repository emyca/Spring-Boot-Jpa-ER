package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VendeeDtoRequest(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        String street,
        String building,
        String apartment) {
}
