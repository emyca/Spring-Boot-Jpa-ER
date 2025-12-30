package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.controller;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.ProjectDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.ProjectDtoResponse;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.service.ProjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(
            @Qualifier("ProjectServiceImpl")
            ProjectService service) {
        this.service = service;
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectDtoResponse> createProject(
            @RequestBody ProjectDtoRequest request) {
        ProjectDtoResponse response = service.createProject(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/employees/{id}/projects")
    public ResponseEntity<ProjectDtoResponse> setProjectToByEmployeeId(
            @PathVariable("id") Long id,
            @RequestBody ProjectDtoRequest request) {
        ProjectDtoResponse response = service.setProjectToByEmployeeId(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/projects")
    public ResponseEntity<ProjectDtoResponse> getAllProjects() {
        ProjectDtoResponse response = service.getAllProjects();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDtoResponse> getProjectById(
            @PathVariable("id") Long id) {
        ProjectDtoResponse response = service.getProjectById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectDtoResponse> updateProjectById(
            @PathVariable("id") Long id,
            @RequestBody ProjectDtoRequest request) {
        ProjectDtoResponse response = service.updateProjectById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/employees/{employeeId}/projects/{projectId}")
    public ResponseEntity<ProjectDtoResponse> unsetProjectByIdFromEmployeeId(
            @PathVariable("employeeId") Long employeeId,
            @PathVariable("projectId") Long projectId) {
        ProjectDtoResponse response =
                service.unsetProjectByIdFromEmployeeId(employeeId, projectId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<ProjectDtoResponse> deleteProjectById(
            @PathVariable(value = "id") Long id) {
        ProjectDtoResponse response = service.deleteProjectById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
