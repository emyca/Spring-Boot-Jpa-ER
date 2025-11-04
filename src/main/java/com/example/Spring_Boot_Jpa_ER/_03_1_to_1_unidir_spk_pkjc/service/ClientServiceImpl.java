package com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.service;

import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoResponse;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoRequest;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.dto.ClientDtoResponse;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Client;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.entity.Residence;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.mapper.ClientMapper;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.model.ClientModel;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.repository.ClientRepository;
import com.example.Spring_Boot_Jpa_ER._03_1_to_1_unidir_spk_pkjc.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("ClientServiceImpl")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ResidenceRepository residenceRepository;

    @Override
    public ClientDtoResponse createClient(ClientDtoRequest request) {
        Client client = clientRepository
                .saveAndFlush(clientMapper
                        .dtoCreateToEntity(request));
        return (client != null)
                ? new ClientDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(ClientDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .client(ClientModel.getModel(client))
                .build()
                : new ClientDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(ClientDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public ClientDtoResponse getAllClients() {
        List<Client> list = clientRepository.findAll();
        if (!list.isEmpty()) {
            List<ClientModel> _list = new ArrayList<>();
            for (Client client : list)
                _list.add(ClientModel.getModel(client));
            return new ClientDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ClientDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .clientList(_list)
                    .build();
        } else
            return new ClientDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(ClientDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .clientList(Collections.emptyList())
                    .build();
    }

    @Override
    public ClientDtoResponse getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElse(null);
        return (client != null)
                ? new ClientDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(ClientDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .client(ClientModel.getModel(client))
                .build()
                : new ClientDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ClientDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public ClientDtoResponse updateClientById(Long id, ClientDtoRequest request) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Optional<Residence> residenceOptional = residenceRepository.findById(id);

        Client client = (clientOptional.isPresent() & residenceOptional.isPresent())
                ? clientRepository.saveAndFlush(
                clientMapper.dtoUpdateByIdToEntity(
                        id, request,
                        clientOptional.get(),
                        residenceOptional.get()))
                : null;

        return (client != null)
                ? new ClientDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(ClientDtoResponse
                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                        .formatted(id))
                .client(ClientModel.getModel(client))
                .build()
                : new ClientDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ClientDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public ClientDtoResponse deleteClientById(Long id) {
        if (clientRepository.findById(id).isPresent()) {
            clientRepository.deleteById(id);
            return new ClientDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(BuyerDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new ClientDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(BuyerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
