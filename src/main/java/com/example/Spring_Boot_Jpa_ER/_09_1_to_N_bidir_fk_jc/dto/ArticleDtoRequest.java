package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ArticleDtoRequest(
        Long id,
        String title,
        String content) {
}
