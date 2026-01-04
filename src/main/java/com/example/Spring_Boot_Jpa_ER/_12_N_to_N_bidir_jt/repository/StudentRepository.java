package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.repository;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentsByCoursesId(Long courseId);
}
