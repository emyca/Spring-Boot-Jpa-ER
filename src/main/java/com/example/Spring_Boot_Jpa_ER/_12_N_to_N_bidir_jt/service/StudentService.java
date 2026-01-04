package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.service;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.StudentDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.StudentDtoResponse;

public interface StudentService {
    StudentDtoResponse createStudent(StudentDtoRequest request);
    StudentDtoResponse getAllStudents();
    StudentDtoResponse getStudentById(Long id);
    StudentDtoResponse getAllStudentsByCourseId(Long courseId);
    StudentDtoResponse updateStudentById(Long id, StudentDtoRequest request);
    StudentDtoResponse deleteStudentById(Long id);
}
