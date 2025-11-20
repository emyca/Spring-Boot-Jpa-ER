package com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.repository;

import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
}
