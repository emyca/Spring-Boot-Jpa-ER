package com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.mapper;

import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.entity.Habitation;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.entity.Purchaser;
import org.springframework.stereotype.Component;

@Component
public class PurchaserMapper {

    public Purchaser dtoCreateToEntity(PurchaserDtoRequest request) {
        return Purchaser.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .habitation(getHabitation(request))
                .build();
    }

    private Habitation getHabitation(PurchaserDtoRequest request) {
        return Habitation.builder()
                .city(request.city())
                .street(request.street())
                .building(request.building())
                .apartment(request.apartment())
                .build();
    }

    public Purchaser dtoUpdateByIdToEntity(Long id,
                                           PurchaserDtoRequest request,
                                           Purchaser purchaserToUpdate,
                                           Habitation habitationToUpdate) {
        purchaserToUpdate.setId(id);
        habitationToUpdate.setId(id);
        purchaserToUpdate.setFirstName(request.firstName());
        purchaserToUpdate.setLastName(request.lastName());
        purchaserToUpdate.setEmail(request.email());
        purchaserToUpdate.setHabitation(
                getHabitationToUpdate(request, habitationToUpdate));
        return purchaserToUpdate;
    }

    public Habitation getHabitationToUpdate(PurchaserDtoRequest request,
                                            Habitation habitationToUpdate) {
        habitationToUpdate.setCity(request.city());
        habitationToUpdate.setStreet(request.street());
        habitationToUpdate.setBuilding(request.building());
        habitationToUpdate.setApartment(request.apartment());
        return habitationToUpdate;
    }
}
