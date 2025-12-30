package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.service;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.EmployeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.EmployeeDtoResponse;

public interface EmployeeService {
    EmployeeDtoResponse createEmployee(EmployeeDtoRequest request);
    EmployeeDtoResponse getAllEmployees();
    EmployeeDtoResponse getEmployeeById(Long id);
    EmployeeDtoResponse getAllEmployeesByProjectId(Long projectId);
    EmployeeDtoResponse updateEmployeeById(Long id, EmployeeDtoRequest request);
    EmployeeDtoResponse deleteEmployeeById(Long id);
}
