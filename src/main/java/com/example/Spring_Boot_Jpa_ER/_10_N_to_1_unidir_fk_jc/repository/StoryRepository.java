package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.repository;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
