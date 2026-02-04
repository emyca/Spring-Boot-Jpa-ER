package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.mapper;

import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.dto.ProjectDtoRequest;
import com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project dtoCreateToEntity(ProjectDtoRequest request) {
        return Project.builder()
                .id(request.id())
                .name(request.name())
                .build();
    }

    public Project dtoUpdateByIdToEntity(ProjectDtoRequest request,
                                         Project projectToUpdate) {
        projectToUpdate.setName(request.name());
        return projectToUpdate;
    }
}
