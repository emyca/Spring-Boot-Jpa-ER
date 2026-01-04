package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.repository;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findCoursesByStudentsId(Long studentId);
    Course findCourseByName(String name);
}
