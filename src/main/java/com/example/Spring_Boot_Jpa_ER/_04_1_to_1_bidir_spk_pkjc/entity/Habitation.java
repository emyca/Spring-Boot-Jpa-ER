package com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.entity;

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
@Entity(name = "habitations")
public class Habitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "building")
    private String building;
    @Column(name = "apartment")
    private String apartment;
    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Purchaser purchaser;
}
