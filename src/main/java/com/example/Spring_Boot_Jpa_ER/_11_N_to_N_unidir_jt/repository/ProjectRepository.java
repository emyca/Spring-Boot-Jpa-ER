package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.repository;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByName(String name);
}
