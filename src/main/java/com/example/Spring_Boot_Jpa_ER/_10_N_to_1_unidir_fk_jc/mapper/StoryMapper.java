package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.mapper;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.StoryDtoRequest;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.entity.Story;
import org.springframework.stereotype.Component;

@Component
public class StoryMapper {

    public Story dtoCreateToEntity(StoryDtoRequest request) {
        return Story.builder()
                .id(request.id())
                .title(request.title())
                .content(request.content())
                .build();
    }

    public Story dtoUpdateByIdToEntity(StoryDtoRequest request,
                                       Story storyToUpdate) {
        storyToUpdate.setTitle(request.title());
        storyToUpdate.setContent(request.content());
        return storyToUpdate;
    }
}
