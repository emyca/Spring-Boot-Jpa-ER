package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpinionDtoRequest(
        Long id,
        String consideration) {
}
