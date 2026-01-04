package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.service;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.CourseDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.CourseDtoResponse;

public interface CourseService {
    CourseDtoResponse createCourse(CourseDtoRequest request);
    CourseDtoResponse setCourseToByStudentId(Long id, CourseDtoRequest request);
    CourseDtoResponse getAllCourses();
    CourseDtoResponse getCourseById(Long id);
    CourseDtoResponse getAllCoursesByStudentId(Long studentId);
    CourseDtoResponse updateCourseById(Long id, CourseDtoRequest request);
    CourseDtoResponse unsetCourseByIdFromStudentId(Long studentId, Long courseId);
    CourseDtoResponse deleteCourseById(Long id);
}
