package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.repository;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Vendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendeeRepository extends JpaRepository<Vendee, Long> {
}
