package com.example.Spring_Boot_Jpa_ER._11_N_to_N_unidir_jt.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(
                    name = "employee_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "project_id"
            )
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Project> projects;

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void removeProject(long projectId) {
        this.projects.stream()
                .filter(project ->
                        project.getId() == projectId)
                .findFirst()
                .ifPresent(project ->
                        this.projects.remove(project));
    }
}
