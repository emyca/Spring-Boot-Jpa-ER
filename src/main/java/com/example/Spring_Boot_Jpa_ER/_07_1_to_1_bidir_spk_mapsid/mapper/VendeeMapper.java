package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.mapper;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Abode;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Vendee;
import org.springframework.stereotype.Component;

@Component
public class VendeeMapper {

    public Vendee dtoCreateToEntity(VendeeDtoRequest request) {
        return Vendee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .abode(null)
                .build();
    }

    public Vendee dtoUpdateByIdToEntity(Long id,
                                        VendeeDtoRequest request,
                                        Vendee vendeeToUpdate,
                                        Abode abodeToUpdate) {
        vendeeToUpdate.setId(id);
        abodeToUpdate.setId(id);
        vendeeToUpdate.setFirstName(request.firstName());
        vendeeToUpdate.setLastName(request.lastName());
        vendeeToUpdate.setEmail(request.email());
        vendeeToUpdate.setAbode(
                getAbodeToUpdate(request, abodeToUpdate));
        return vendeeToUpdate;
    }

    private Abode getAbodeToUpdate(VendeeDtoRequest request,
                                   Abode abodeToUpdate) {
        abodeToUpdate.setCity(request.city());
        abodeToUpdate.setStreet(request.street());
        abodeToUpdate.setBuilding(request.building());
        abodeToUpdate.setApartment(request.apartment());
        return abodeToUpdate;
    }
}
