package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.CommentDtoRequest;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.CommentDtoResponse;

public interface CommentService {
    CommentDtoResponse createCommentByPostId(Long id, CommentDtoRequest request);
    CommentDtoResponse getAllCommentsByPostId(Long id);
    CommentDtoResponse getCommentById(Long id);
    CommentDtoResponse updateCommentById(Long id, CommentDtoRequest request);
    CommentDtoResponse deleteCommentById(Long id);
}
