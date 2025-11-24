package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity;

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
@Entity(name = "abodes")
public class Abode {
    @Id
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
    @JoinColumn(name = "id")
    @MapsId
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Vendee vendee;
}
