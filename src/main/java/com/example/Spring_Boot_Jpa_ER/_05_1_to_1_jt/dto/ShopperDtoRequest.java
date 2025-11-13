package com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ShopperDtoRequest(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        String street,
        String building,
        String apartment) {
}
