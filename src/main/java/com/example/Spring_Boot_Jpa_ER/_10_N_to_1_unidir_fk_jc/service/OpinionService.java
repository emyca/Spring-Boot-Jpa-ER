package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.OpinionDtoRequest;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.OpinionDtoResponse;

public interface OpinionService {
    OpinionDtoResponse createOpinionByStoryId(Long id, OpinionDtoRequest request);
    OpinionDtoResponse getAllOpinionsByStoryId(Long id);
    OpinionDtoResponse getOpinionById(Long id);
    OpinionDtoResponse updateOpinionById(Long id, OpinionDtoRequest request);
    OpinionDtoResponse deleteOpinionById(Long id);
}
