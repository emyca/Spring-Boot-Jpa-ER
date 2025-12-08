package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.PostDtoRequest;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.PostDtoResponse;

public interface PostService {
    PostDtoResponse createPost(PostDtoRequest request);
    PostDtoResponse getAllPosts();
    PostDtoResponse getPostById(Long id);
    PostDtoResponse updatePostById(Long id, PostDtoRequest request);
    PostDtoResponse deletePostById(Long id);
}
