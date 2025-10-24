package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.repository;

import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
