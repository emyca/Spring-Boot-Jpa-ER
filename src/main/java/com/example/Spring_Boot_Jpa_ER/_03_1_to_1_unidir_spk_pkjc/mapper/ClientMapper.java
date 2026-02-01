package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.mapper;

import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoRequest;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Client;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Residence;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client dtoCreateToEntity(ClientDtoRequest request) {
        return Client.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .residence(getResidence(request))
                .build();
    }

    private Residence getResidence(ClientDtoRequest request) {
        return Residence.builder()
                .city(request.city())
                .street(request.street())
                .building(request.building())
                .apartment(request.apartment())
                .build();
    }

    public Client dtoUpdateByIdToEntity(Long id,
                                        ClientDtoRequest request,
                                        Client clientToUpdate,
                                        Residence residenceToUpdate) {
        clientToUpdate.setId(id);
        residenceToUpdate.setId(id);
        clientToUpdate.setFirstName(request.firstName());
        clientToUpdate.setLastName(request.lastName());
        clientToUpdate.setEmail(request.email());
        clientToUpdate.setResidence(
                getResidenceToUpdate(request, residenceToUpdate));
        return clientToUpdate;
    }

    private Residence getResidenceToUpdate(ClientDtoRequest request,
                                           Residence residenceToUpdate) {
        residenceToUpdate.setCity(request.city());
        residenceToUpdate.setStreet(request.street());
        residenceToUpdate.setBuilding(request.building());
        residenceToUpdate.setApartment(request.apartment());
        return residenceToUpdate;
    }
}
