package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.mapper;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.dto.ArticleDtoRequest;
import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public Article dtoCreateToEntity(ArticleDtoRequest request) {
        return Article.builder()
                .id(request.id())
                .title(request.title())
                .content(request.content())
                .build();
    }

    public Article dtoUpdateByIdToEntity(Long id,
                                         ArticleDtoRequest request,
                                         Article articleToUpdate) {
        articleToUpdate.setId(id);
        articleToUpdate.setTitle(request.title());
        articleToUpdate.setContent(request.content());
        return articleToUpdate;
    }
}
