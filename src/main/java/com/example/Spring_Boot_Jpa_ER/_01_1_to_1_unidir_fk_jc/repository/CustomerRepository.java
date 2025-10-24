package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.repository;

import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
