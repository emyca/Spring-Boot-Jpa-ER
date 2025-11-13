package com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.repository;

import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
