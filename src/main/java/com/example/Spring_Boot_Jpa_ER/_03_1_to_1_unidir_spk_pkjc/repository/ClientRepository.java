package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.repository;

import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
