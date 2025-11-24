package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "vendees")
public class Vendee {
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
    @OneToOne(mappedBy = "vendee", cascade = CascadeType.ALL)
    private Abode abode;
}
