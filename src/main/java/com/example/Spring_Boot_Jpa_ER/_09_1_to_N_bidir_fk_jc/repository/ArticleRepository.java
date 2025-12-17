package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.repository;

import com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
