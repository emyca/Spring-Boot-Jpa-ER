package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.mapper;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.PostDtoRequest;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post dtoCreateToEntity(PostDtoRequest request) {
        return Post.builder()
                .id(request.id())
                .title(request.title())
                .content(request.content())
                .build();
    }

    public Post dtoUpdateByIdToEntity(Long id,
                                      PostDtoRequest request,
                                      Post postToUpdate) {
        postToUpdate.setId(id);
        postToUpdate.setTitle(request.title());
        postToUpdate.setContent(request.content());
        return postToUpdate;
    }
}
