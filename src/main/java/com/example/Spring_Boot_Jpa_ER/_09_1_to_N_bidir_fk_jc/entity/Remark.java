package com.example.Spring_Boot_Jpa_ER._09_1_to_N_bidir_fk_jc.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "remarks")
public class Remark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "consideration")
    private String consideration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "article_id",
            referencedColumnName = "id"
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Article article;
}
