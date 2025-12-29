package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.mapper;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.EmployeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee dtoCreateToEntity(EmployeeDtoRequest request) {
        Employee employee = new Employee();
        employee.setId(request.id());
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        return employee;
    }

    public Employee dtoUpdateByIdToEntity(EmployeeDtoRequest request,
                                          Employee employeeToUpdate) {
        employeeToUpdate.setFirstName(request.firstName());
        employeeToUpdate.setLastName(request.lastName());
        employeeToUpdate.setEmail(request.email());
        return employeeToUpdate;
    }
}
