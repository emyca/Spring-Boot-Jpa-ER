package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.repository;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByProjectsId(Long projectId);
}
