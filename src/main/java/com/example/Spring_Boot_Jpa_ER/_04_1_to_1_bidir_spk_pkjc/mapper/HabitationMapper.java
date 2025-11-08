package com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.mapper;

import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.entity.Habitation;
import org.springframework.stereotype.Component;

@Component
public class HabitationMapper {

    public Habitation dtoCreateToEntity(PurchaserDtoRequest request) {
        Habitation habitation = new Habitation();
        habitation.setCity(request.city());
        habitation.setStreet(request.street());
        habitation.setBuilding(request.building());
        habitation.setApartment(request.apartment());
        return habitation;
    }

    public Habitation dtoUpdateToEntity(PurchaserDtoRequest request,
                                        Habitation habitationToUpdate) {
        habitationToUpdate.setCity(request.city());
        habitationToUpdate.setStreet(request.street());
        habitationToUpdate.setBuilding(request.building());
        habitationToUpdate.setApartment(request.apartment());
        return habitationToUpdate;
    }
}
