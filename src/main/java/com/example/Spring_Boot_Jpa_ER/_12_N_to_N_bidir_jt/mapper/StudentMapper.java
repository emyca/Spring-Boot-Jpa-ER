package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.mapper;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.StudentDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student dtoCreateToEntity(StudentDtoRequest request) {
        return Student.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
    }

    public Student dtoUpdateByIdToEntity(StudentDtoRequest request,
                                         Student studentToUpdate) {
        studentToUpdate.setFirstName(request.firstName());
        studentToUpdate.setLastName(request.lastName());
        studentToUpdate.setEmail(request.email());
        return studentToUpdate;
    }
}
