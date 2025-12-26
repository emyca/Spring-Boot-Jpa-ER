package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.StoryDtoRequest;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.StoryDtoResponse;

public interface StoryService {
    StoryDtoResponse createStory(StoryDtoRequest request);
    StoryDtoResponse getAllStories();
    StoryDtoResponse getStoryById(Long id);
    StoryDtoResponse updateStoryById(Long id, StoryDtoRequest request);
    StoryDtoResponse deleteStoryById(Long id);
}
