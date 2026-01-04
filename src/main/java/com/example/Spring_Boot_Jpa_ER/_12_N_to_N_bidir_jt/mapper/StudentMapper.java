package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.mapper;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.StudentDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student dtoCreateToEntity(StudentDtoRequest request) {
        Student student = new Student();
        student.setId(request.id());
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        return student;
    }

    public Student dtoUpdateByIdToEntity(StudentDtoRequest request,
                                         Student studentToUpdate) {
        studentToUpdate.setFirstName(request.firstName());
        studentToUpdate.setLastName(request.lastName());
        studentToUpdate.setEmail(request.email());
        return studentToUpdate;
    }
}
