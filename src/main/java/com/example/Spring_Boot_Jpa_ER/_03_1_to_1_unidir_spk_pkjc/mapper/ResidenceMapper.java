package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.mapper;

import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoRequest;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Residence;
import org.springframework.stereotype.Component;

@Component
public class ResidenceMapper {

    public Residence dtoCreateToEntity(ClientDtoRequest request) {
        Residence residence = new Residence();
        residence.setCity(request.city());
        residence.setStreet(request.street());
        residence.setBuilding(request.building());
        residence.setApartment(request.apartment());
        return residence;
    }

    public Residence dtoUpdateToEntity(ClientDtoRequest request,
                                       Residence residenceToUpdate) {
        residenceToUpdate.setCity(request.city());
        residenceToUpdate.setStreet(request.street());
        residenceToUpdate.setBuilding(request.building());
        residenceToUpdate.setApartment(request.apartment());
        return residenceToUpdate;
    }
}
