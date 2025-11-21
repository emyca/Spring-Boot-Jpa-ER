package com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.mapper;

import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.entity.Domicile;
import org.springframework.stereotype.Component;

@Component
public class DomicileMapper {

    public Domicile dtoCreateToEntity(ConsumerDtoRequest request) {
        Domicile domicile = new Domicile();
        domicile.setCity(request.city());
        domicile.setStreet(request.street());
        domicile.setBuilding(request.building());
        domicile.setApartment(request.apartment());
        return domicile;
    }

    public Domicile dtoUpdateToEntity(ConsumerDtoRequest request,
                                      Domicile domicileToUpdate) {
        domicileToUpdate.setCity(request.city());
        domicileToUpdate.setStreet(request.street());
        domicileToUpdate.setBuilding(request.building());
        domicileToUpdate.setApartment(request.apartment());
        return domicileToUpdate;
    }
}
