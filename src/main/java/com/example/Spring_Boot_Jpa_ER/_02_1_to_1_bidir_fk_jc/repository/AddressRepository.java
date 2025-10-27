package com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.repository;

import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
