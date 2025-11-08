package com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.repository;

import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.entity.Purchaser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaserRepository extends JpaRepository<Purchaser, Long> {
}
