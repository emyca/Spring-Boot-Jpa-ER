package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.repository;

import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
}
