package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.controller;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.StudentDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.StudentDtoResponse;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.service.StudentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class StudentController {

    private final StudentService service;

    public StudentController(
            @Qualifier("StudentServiceImpl")
            StudentService service) {
        this.service = service;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDtoResponse> createStudent(
            @RequestBody StudentDtoRequest request) {
        StudentDtoResponse response = service.createStudent(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/students")
    public ResponseEntity<StudentDtoResponse> getAllStudents() {
        StudentDtoResponse response = service.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDtoResponse> getStudentById(
            @PathVariable("id") Long id) {
        StudentDtoResponse response = service.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/courses/{id}/students")
    public ResponseEntity<StudentDtoResponse> getAllStudentsByCourseId(
            @PathVariable("id") Long id) {
        StudentDtoResponse response = service.getAllStudentsByCourseId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDtoResponse> updateStudentById(
            @PathVariable("id") Long id,
            @RequestBody StudentDtoRequest request) {
        StudentDtoResponse response = service.updateStudentById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentDtoResponse> deleteStudentById(
            @PathVariable(value = "id") Long id) {
        StudentDtoResponse response = service.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
