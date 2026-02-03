package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.mapper;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Abode;
import org.springframework.stereotype.Component;

@Component
public class AbodeMapper {

    public Abode dtoCreateToEntity(VendeeDtoRequest request) {
        return Abode.builder()
                .city(request.city())
                .street(request.street())
                .building(request.building())
                .apartment(request.apartment())
                .build();
    }
}
