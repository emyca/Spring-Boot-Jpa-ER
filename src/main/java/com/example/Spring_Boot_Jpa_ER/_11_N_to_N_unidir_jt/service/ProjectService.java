package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.service;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.ProjectDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.ProjectDtoResponse;

public interface ProjectService {
    ProjectDtoResponse createProject(ProjectDtoRequest request);
    ProjectDtoResponse setProjectToByEmployeeId(Long id, ProjectDtoRequest request);
    ProjectDtoResponse getAllProjects();
    ProjectDtoResponse getProjectById(Long id);
    ProjectDtoResponse updateProjectById(Long id, ProjectDtoRequest request);
    ProjectDtoResponse unsetProjectByIdFromEmployeeId(Long employeeId, Long projectId);
    ProjectDtoResponse deleteProjectById(Long id);
}
