package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.mapper;

import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoRequest;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Client;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Residence;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client dtoCreateToEntity(ClientDtoRequest request) {
        Client client = new Client();
        client.setId(request.id());
        client.setFirstName(request.firstName());
        client.setLastName(request.lastName());
        client.setEmail(request.email());
        client.setResidence(getResidence(request));
        return client;
    }

    private Residence getResidence(ClientDtoRequest request) {
        Residence residence = new Residence();
        residence.setCity(request.city());
        residence.setStreet(request.street());
        residence.setBuilding(request.building());
        residence.setApartment(request.apartment());
        return residence;
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
