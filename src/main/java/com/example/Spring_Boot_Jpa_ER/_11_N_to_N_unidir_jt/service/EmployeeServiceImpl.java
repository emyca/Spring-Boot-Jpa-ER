package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.service;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.EmployeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.EmployeeDtoResponse;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity.Employee;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.mapper.EmployeeMapper;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.model.EmployeeModel;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.repository.EmployeeRepository;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.repository.ProjectRepository;
import com.example.Spring_Boot_Jpa_ER.common.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("EmployeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public EmployeeServiceImpl(
            EmployeeMapper employeeMapper,
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public EmployeeDtoResponse createEmployee(EmployeeDtoRequest request) {
        Employee employee = employeeRepository.save(
                employeeMapper.dtoCreateToEntity(request));
        return (employee != null)
                ? new EmployeeDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(EmployeeDtoResponse
                        .Message.SUCCESS_SAVE_MSG.getMessage())
                .employee(EmployeeModel.getModel(employee))
                .build()
                : new EmployeeDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(EmployeeDtoResponse
                        .Message.FAILURE_SAVE_MSG.getMessage())
                .build();
    }

    @Override
    public EmployeeDtoResponse getAllEmployees() {
        List<Employee> list = employeeRepository.findAll();
        if (!list.isEmpty()) {
            List<EmployeeModel> _list = new ArrayList<>();
            for (Employee employee : list)
                _list.add(EmployeeModel.getModel(employee));
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .employees(_list)
                    .build();
        } else
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(EmployeeDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .employees(Collections.emptyList())
                    .build();
    }

    @Override
    public EmployeeDtoResponse getEmployeeById(Long id)
            throws ResourceException {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Employee with id %s has not been found!"
                                            .formatted(id)));
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse
                            .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .employee(EmployeeModel.getModel(employee))
                    .build();
        } catch (ResourceException e) {
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public EmployeeDtoResponse getAllEmployeesByProjectId(Long id) {
        try {
            if (!projectRepository.existsById(id)) {
                throw new ResourceException(
                        "Project with id %s has not been found!"
                                .formatted(id));
            }
            List<Employee> list = employeeRepository.findEmployeesByProjectsId(id);
            return (!list.isEmpty())
                    ? new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .projectId(id)
                    .employees(EmployeeModel.getModel(list))
                    .build()
                    : new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(EmployeeDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .employees(Collections.emptyList())
                    .build();
        } catch (ResourceException e) {
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public EmployeeDtoResponse updateEmployeeById(Long id, EmployeeDtoRequest request)
            throws ResourceException {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Employee with id %s has not been found!"
                                            .formatted(id)));
            Employee _employee = employeeRepository.save(employeeMapper
                    .dtoUpdateByIdToEntity(request, employee));
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .employee(EmployeeModel.getModel(_employee))
                    .build();
        } catch (ResourceException e) {
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public EmployeeDtoResponse deleteEmployeeById(Long id)
            throws ResourceException {
        try {
            employeeRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Employee with id %s has not been found!"
                                            .formatted(id)));
            employeeRepository.deleteById(id);
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(EmployeeDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        } catch (ResourceException e) {
            return new EmployeeDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }
}
