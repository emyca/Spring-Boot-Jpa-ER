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
        Residence residence = new ResidenceMapper()
                .dtoCreateToEntity(request);
        client.setResidence(residence);
        return client;
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
        Residence residence = new ResidenceMapper()
                .dtoUpdateToEntity(request, residenceToUpdate);
        clientToUpdate.setResidence(residence);
        return clientToUpdate;
    }
}
