package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.mapper;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Abode;
import org.springframework.stereotype.Component;

@Component
public class AbodeMapper {

    public Abode dtoCreateToEntity(VendeeDtoRequest request) {
        Abode abode = new Abode();
        abode.setCity(request.city());
        abode.setStreet(request.street());
        abode.setBuilding(request.building());
        abode.setApartment(request.apartment());
        return abode;
    }

    public Abode dtoUpdateToEntity(VendeeDtoRequest request,
                                   Abode abodeToUpdate) {
        abodeToUpdate.setCity(request.city());
        abodeToUpdate.setStreet(request.street());
        abodeToUpdate.setBuilding(request.building());
        abodeToUpdate.setApartment(request.apartment());
        return abodeToUpdate;
    }
}
