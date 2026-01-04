package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.controller;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.CourseDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.CourseDtoResponse;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.service.CourseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class CourseController {

    private final CourseService service;

    public CourseController(
            @Qualifier("CourseServiceImpl")
            CourseService service) {
        this.service = service;
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseDtoResponse> createCourse(
            @RequestBody CourseDtoRequest request) {
        CourseDtoResponse response = service.createCourse(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/students/{id}/courses")
    public ResponseEntity<CourseDtoResponse> setCourseToByStudentId(
            @PathVariable("id") Long id,
            @RequestBody CourseDtoRequest request) {
        CourseDtoResponse response = service.setCourseToByStudentId(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/courses")
    public ResponseEntity<CourseDtoResponse> getAllCourses() {
        CourseDtoResponse response = service.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDtoResponse> getCourseById(
            @PathVariable("id") Long id) {
        CourseDtoResponse response = service.getCourseById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/students/{id}/courses")
    public ResponseEntity<CourseDtoResponse> getAllCoursesByStudentId(
            @PathVariable("id") Long id) {
        CourseDtoResponse response = service.getAllCoursesByStudentId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDtoResponse> updateCourseById(
            @PathVariable("id") Long id,
            @RequestBody CourseDtoRequest request) {
        CourseDtoResponse response = service.updateCourseById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<CourseDtoResponse> unsetCourseByIdFromStudentId(
            @PathVariable("studentId") Long studentId,
            @PathVariable("courseId") Long courseId) {
        CourseDtoResponse response =
                service.unsetCourseByIdFromStudentId(studentId, courseId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<CourseDtoResponse> deleteCourseById(
            @PathVariable("id") Long id) {
        CourseDtoResponse response = service.deleteCourseById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
