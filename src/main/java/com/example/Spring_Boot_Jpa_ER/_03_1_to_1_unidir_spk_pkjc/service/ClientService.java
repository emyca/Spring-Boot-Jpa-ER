package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.service;

import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoRequest;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoResponse;

public interface ClientService {
    ClientDtoResponse createClient(ClientDtoRequest request);
    ClientDtoResponse getAllClients();
    ClientDtoResponse getClientById(Long id);
    ClientDtoResponse updateClientById(Long id, ClientDtoRequest request);
    ClientDtoResponse deleteClientById(Long id);
}
