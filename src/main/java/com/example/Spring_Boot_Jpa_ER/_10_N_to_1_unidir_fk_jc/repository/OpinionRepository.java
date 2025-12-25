package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.repository;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion> findOpinionsByStoryId(Long storyId);
}
