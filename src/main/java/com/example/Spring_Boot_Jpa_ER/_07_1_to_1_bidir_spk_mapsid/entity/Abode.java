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
    // @GeneratedValue(strategy = GenerationType.IDENTITY) with @Id
    // is not used, since it is not necessary to generate an identifier
    // for this entity.
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
    // @OneToOne defines a one-to-one association with another entity,
    // which has a one-to-one multiplicity.
    @OneToOne
    // @JoinColumn marks a column as a join column
    // for an entity or item collection association.
    @JoinColumn(name = "id")
    // @MapsId simplifies One-to-One relationships by allowing two
    // entities to share a single primary key.
    // This helps implement the shared primary key strategy,
    // by telling Hibernate to match the primary key of a child
    // entity with the primary key of its related parent entity.
    // @OneToOne and @MapsId indicate that both the source and target
    // entities share a primary key value.
    @MapsId
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Vendee vendee;
}
