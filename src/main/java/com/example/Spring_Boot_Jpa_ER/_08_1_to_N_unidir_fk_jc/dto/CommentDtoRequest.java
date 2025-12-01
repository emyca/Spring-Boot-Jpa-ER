package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CommentDtoRequest(
        Long id,
        String consideration) {
}
