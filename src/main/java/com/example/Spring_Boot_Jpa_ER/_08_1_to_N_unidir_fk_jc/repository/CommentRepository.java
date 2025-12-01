package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.repository;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
