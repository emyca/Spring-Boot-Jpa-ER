package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.mapper;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.EmployeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee dtoCreateToEntity(EmployeeDtoRequest request) {
        return Employee.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
    }

    public Employee dtoUpdateByIdToEntity(EmployeeDtoRequest request,
                                          Employee employeeToUpdate) {
        employeeToUpdate.setFirstName(request.firstName());
        employeeToUpdate.setLastName(request.lastName());
        employeeToUpdate.setEmail(request.email());
        return employeeToUpdate;
    }
}
