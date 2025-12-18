package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.RemarkDtoRequest;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.RemarkDtoResponse;

public interface RemarkService {
    RemarkDtoResponse createRemarkByArticleId(Long id, RemarkDtoRequest request);
    RemarkDtoResponse getAllRemarksByArticleId(Long id);
    RemarkDtoResponse getRemarkById(Long id);
    RemarkDtoResponse updateRemarkById(Long id, RemarkDtoRequest request);
    RemarkDtoResponse deleteRemarkById(Long id);
}
