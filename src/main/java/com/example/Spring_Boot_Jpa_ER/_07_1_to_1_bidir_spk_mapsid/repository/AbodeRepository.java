package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.repository;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Abode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbodeRepository extends JpaRepository<Abode, Long> {
}
