package com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.mapper;

import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.dto.CourseDtoRequest;
import com.example.Spring_Boot_Jpa_ER._12_N_to_N_bidir_jt.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course dtoCreateToEntity(CourseDtoRequest request) {
        Course course = new Course();
        course.setId(request.id());
        course.setName(request.name());
        return course;
    }

    public Course dtoUpdateByIdToEntity(CourseDtoRequest request,
                                        Course courseToUpdate) {
        courseToUpdate.setName(request.name());
        return courseToUpdate;
    }
}
