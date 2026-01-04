package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.service;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.StudentDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.StudentDtoResponse;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.entity.Student;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.mapper.StudentMapper;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.model.StudentModel;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.repository.CourseRepository;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.repository.StudentRepository;
import com.example.Spring_Boot_Jpa_ER.common.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(
            StudentMapper studentMapper,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public StudentDtoResponse createStudent(StudentDtoRequest request) {
        Student student = studentRepository.save(
                studentMapper.dtoCreateToEntity(request));
        return (student != null)
                ? new StudentDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(StudentDtoResponse
                        .Message.SUCCESS_SAVE_MSG.getMessage())
                .student(StudentModel.getModel(student))
                .build()
                : new StudentDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(StudentDtoResponse
                        .Message.FAILURE_SAVE_MSG.getMessage())
                .build();
    }

    @Override
    public StudentDtoResponse getAllStudents() {
        List<Student> list = studentRepository.findAll();
        if (!list.isEmpty()) {
            List<StudentModel> _list = new ArrayList<>();
            for (Student student : list)
                _list.add(StudentModel.getModel(student));
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StudentDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .students(_list)
                    .build();
        } else
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(StudentDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .students(Collections.emptyList())
                    .build();
    }

    @Override
    public StudentDtoResponse getStudentById(Long id)
            throws ResourceException {
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Student with id %s has not been found!"
                                            .formatted(id)));
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StudentDtoResponse
                            .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .student(StudentModel.getModel(student))
                    .build();
        } catch (ResourceException e) {
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public StudentDtoResponse getAllStudentsByCourseId(Long id)
            throws ResourceException {
        try {
            if (!courseRepository.existsById(id)) {
                throw new ResourceException(
                        "Course with id %s has not been found!"
                                .formatted(id));
            }
            List<Student> list = studentRepository.findStudentsByCoursesId(id);
            return (!list.isEmpty())
                    ? new StudentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StudentDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .courseId(id)
                    .students(StudentModel.getModel(list))
                    .build()
                    : new StudentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(StudentDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .students(Collections.emptyList())
                    .build();
        } catch (ResourceException e) {
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public StudentDtoResponse updateStudentById(Long id, StudentDtoRequest request)
            throws ResourceException {
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Student with id %s has not been found!"
                                            .formatted(id)));
            Student _student = studentRepository.save(studentMapper
                    .dtoUpdateByIdToEntity(request, student));
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StudentDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .student(StudentModel.getModel(_student))
                    .build();
        } catch (ResourceException e) {
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public StudentDtoResponse deleteStudentById(Long id)
            throws ResourceException {
        try {
            studentRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Student with id %s has not been found!"
                                            .formatted(id)));
            studentRepository.deleteById(id);
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StudentDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        } catch (ResourceException e) {
            return new StudentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }
}
