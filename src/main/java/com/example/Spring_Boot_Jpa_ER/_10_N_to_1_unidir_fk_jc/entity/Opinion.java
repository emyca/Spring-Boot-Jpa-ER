package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "opinions")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "consideration")
    private String consideration;
    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;
}
