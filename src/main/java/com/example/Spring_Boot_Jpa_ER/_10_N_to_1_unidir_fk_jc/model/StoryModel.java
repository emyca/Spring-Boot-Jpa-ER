package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.model;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.entity.Story;
import lombok.Data;

@Data
public class StoryModel {
    private Long id;
    private String title;
    private String content;

    public static StoryModel getModel(Story story) {
        StoryModel model = new StoryModel();
        model.setId(story.getId());
        model.setTitle(story.getTitle());
        model.setContent(story.getContent());
        return model;
    }
}
