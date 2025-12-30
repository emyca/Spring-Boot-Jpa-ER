package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.service;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.ProjectDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.ProjectDtoResponse;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity.Employee;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity.Project;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.mapper.ProjectMapper;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.model.ProjectModel;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.repository.EmployeeRepository;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.repository.ProjectRepository;
import com.example.Spring_Boot_Jpa_ER.common.exception.ResourceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectServiceImpl(
            ProjectMapper projectMapper,
            ProjectRepository projectRepository,
            EmployeeRepository employeeRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ProjectDtoResponse createProject(ProjectDtoRequest request) {
        try {
            String name = request.name();
            if (projectRepository.findProjectByName(name) != null)
                throw new ResourceException(
                        "Project with name '%s' already exists!"
                                .formatted(name));
            else {
                Project project = projectRepository.save(
                        projectMapper.dtoCreateToEntity(request));
                return (project != null)
                        ? new ProjectDtoResponse.Builder()
                        .status(HttpStatus.CREATED.value())
                        .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                        .message(ProjectDtoResponse
                                .Message.SUCCESS_SAVE_MSG.getMessage())
                        .project(ProjectModel.getModel(project))
                        .build()
                        : new ProjectDtoResponse.Builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                        .message(ProjectDtoResponse
                                .Message.FAILURE_SAVE_MSG.getMessage())
                        .build();
            }
        } catch (ResourceException e) {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ProjectDtoResponse setProjectToByEmployeeId(
            Long id, ProjectDtoRequest request) {
        try {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ProjectDtoResponse
                            .Message.SUCCESS_SAVE_MSG.getMessage())
                    .employeeId(id)
                    .project(ProjectModel.getModel(setProject(id, request)))
                    .build();
        } catch (ResourceException e) {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    private Project setProject(Long id, ProjectDtoRequest request)
            throws ResourceException {
        return employeeRepository.findById(id).map(employee -> {
            long projectId = request.id();
            // Project exists
            if (projectId != 0L) {
                Project project = projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new ResourceException(
                                        "Project with id %s has not been found!"
                                                .formatted(projectId)));
                employee.addProject(project);
                employeeRepository.save(employee);
                return project;
            }
            // Adds new Project
            Project _project =
                    projectMapper.dtoCreateToEntity(request);
            employee.addProject(_project);
            return projectRepository.save(_project);
        }).orElseThrow(() ->
                new ResourceException(
                        "Employee with id %s has not been found!"
                                .formatted(id)));
    }

    @Override
    public ProjectDtoResponse getAllProjects() {
        List<Project> list = projectRepository.findAll();
        if (!list.isEmpty()) {
            List<ProjectModel> _list = new ArrayList<>();
            for (Project project : list)
                _list.add(ProjectModel.getModel(project));
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ProjectDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .projects(_list)
                    .build();
        } else
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(ProjectDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .projects(Collections.emptyList())
                    .build();
    }

    @Override
    public ProjectDtoResponse getProjectById(Long id) {
        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Project with id %s has not been found!"
                                            .formatted(id)));
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ProjectDtoResponse
                            .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .project(ProjectModel.getModel(project))
                    .build();
        } catch (ResourceException e) {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ProjectDtoResponse updateProjectById(
            Long id, ProjectDtoRequest request) {
        try {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ProjectDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .project(ProjectModel.getModel(
                            updateProject(id, request)))
                    .build();
        } catch (ResourceException e) {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    private Project updateProject(Long id, ProjectDtoRequest request)
            throws ResourceException {
        Project employee = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Project with id %s has not been found!"
                                        .formatted(id)));
        return projectRepository.save(projectMapper
                .dtoUpdateByIdToEntity(request, employee));
    }

    @Override
    public ProjectDtoResponse unsetProjectByIdFromEmployeeId(
            Long employeeId, Long projectId) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Employee with id %s has not been found!"
                                            .formatted(employeeId)));
            employee.removeProject(projectId);
            employeeRepository.save(employee);
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message("Project with id " + projectId + " has been unset " +
                            "from Employee with id " + employeeId + ".")
                    .build();
        } catch (ResourceException e) {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ProjectDtoResponse deleteProjectById(Long id)
            throws ResourceException,
            DataIntegrityViolationException {
        try {
            projectRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Project with id %s has not been found!"
                                            .formatted(id)));
            // Can throw DataIntegrityViolationException
            projectRepository.deleteById(id);
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ProjectDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        } catch (ResourceException e) {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        } catch (DataIntegrityViolationException e) {
            return new ProjectDtoResponse.Builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message("Can't delete the Project with id " + id + ". " +
                            "Referential integrity constraint violation. " +
                            "First unset the Project from Employee(s)!")
                    .build();
        }
    }
}
