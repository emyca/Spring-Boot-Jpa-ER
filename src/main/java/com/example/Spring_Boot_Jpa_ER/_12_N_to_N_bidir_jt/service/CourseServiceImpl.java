package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.service;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.CourseDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.CourseDtoResponse;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.entity.Course;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.entity.Student;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.mapper.CourseMapper;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.model.CourseModel;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.repository.CourseRepository;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.repository.StudentRepository;
import com.example.Spring_Boot_Jpa_ER.common.exception.ResourceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(
            CourseMapper courseMapper,
            CourseRepository courseRepository,
            StudentRepository studentRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public CourseDtoResponse createCourse(CourseDtoRequest request) {
        try {
            String name = request.name();
            if (courseRepository.findCourseByName(name) != null)
                throw new ResourceException(
                        "Course with name '%s' already exists!"
                                .formatted(name));
            else {
                Course course = courseRepository.save(
                        courseMapper.dtoCreateToEntity(request));
                return (course != null)
                        ? new CourseDtoResponse.Builder()
                        .status(HttpStatus.CREATED.value())
                        .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                        .message(CourseDtoResponse
                                .Message.SUCCESS_SAVE_MSG.getMessage())
                        .course(CourseModel.getModel(course))
                        .build()
                        : new CourseDtoResponse.Builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                        .message(CourseDtoResponse
                                .Message.FAILURE_SAVE_MSG.getMessage())
                        .build();
            }
        } catch (ResourceException e) {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CourseDtoResponse setCourseToByStudentId(
            Long id, CourseDtoRequest request) {
        try {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CourseDtoResponse
                            .Message.SUCCESS_SAVE_MSG.getMessage())
                    .course(CourseModel.getModel(setCourse(id, request)))
                    .build();
        } catch (ResourceException e) {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    private Course setCourse(Long id, CourseDtoRequest request)
            throws ResourceException {
        return studentRepository.findById(id).map(student -> {
            long courseId = request.id();
            // Checks if specific Course exists
            if (courseId != 0L) {
                Course course = courseRepository.findById(courseId)
                        .orElseThrow(() ->
                                new ResourceException(
                                        "Course with id %s has not been found!"
                                                .formatted(courseId)));
                student.addCourse(course);
                studentRepository.save(student);
                return course;
            }
            // Adds new Course
            Course _course =
                    courseMapper.dtoCreateToEntity(request);
            student.addCourse(_course);
            return courseRepository.save(_course);
        }).orElseThrow(() ->
                new ResourceException(
                        "Student with id %s has not been found!"
                                .formatted(id)));
    }

    @Override
    public CourseDtoResponse getAllCourses() {
        List<Course> list = courseRepository.findAll();
        if (!list.isEmpty()) {
            List<CourseModel> _list = new ArrayList<>();
            for (Course course : list)
                _list.add(CourseModel.getModel(course));
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CourseDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .courses(_list)
                    .build();
        } else
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(CourseDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .courses(Collections.emptyList())
                    .build();
    }

    @Override
    public CourseDtoResponse getCourseById(Long id) {
        try {
            Course course = courseRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Course with id %s has not been found!"
                                            .formatted(id)));
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CourseDtoResponse
                            .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .course(CourseModel.getModel(course))
                    .build();
        } catch (ResourceException e) {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CourseDtoResponse getAllCoursesByStudentId(Long id)
            throws ResourceException {
        try {
            if (!studentRepository.existsById(id))
                throw new ResourceException(
                        "Student with id %s has not been found!"
                                .formatted(id));
            List<Course> list =
                    courseRepository.findCoursesByStudentsId(id);
            return (!list.isEmpty())
                    ? new CourseDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CourseDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .studentId(id)
                    .courses(CourseModel.getModel(list))
                    .build()
                    : new CourseDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(CourseDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .courses(Collections.emptyList())
                    .build();
        } catch (ResourceException e) {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CourseDtoResponse updateCourseById(
            Long id, CourseDtoRequest request) {
        try {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CourseDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .course(CourseModel.getModel(updateCourse(id, request)))
                    .build();
        } catch (ResourceException e) {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    private Course updateCourse(Long id, CourseDtoRequest request)
            throws ResourceException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Course with id %s has not been found!"
                                        .formatted(id)));
        return courseRepository.save(courseMapper
                .dtoUpdateByIdToEntity(request, course));
    }

    @Override
    public CourseDtoResponse unsetCourseByIdFromStudentId(
            Long studentId, Long courseId) {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Student with id %s has not been found!"
                                            .formatted(studentId)));
            student.removeCourse(courseId);
            studentRepository.save(student);
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message("Course with id " + courseId + " has been unset " +
                            "from Student with id " + studentId + ".")
                    .build();
        } catch (ResourceException e) {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CourseDtoResponse deleteCourseById(Long id)
            throws ResourceException,
            DataIntegrityViolationException {
        try {
            courseRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Course with id %s has not been found!"
                                            .formatted(id)));
            // Can throw DataIntegrityViolationException
            courseRepository.deleteById(id);
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CourseDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        } catch (ResourceException e) {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        } catch (DataIntegrityViolationException e) {
            return new CourseDtoResponse.Builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message("Can't delete the Course with id " + id + ". " +
                            "Referential integrity constraint violation. " +
                            "First unset the Course from Student(s)!")
                    .build();
        }
    }
}
