package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.service;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoResponse;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Abode;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Vendee;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.mapper.AbodeMapper;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.mapper.VendeeMapper;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.model.VendeeModel;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.repository.AbodeRepository;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.repository.VendeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("VendeeServiceImpl")
public class VendeeServiceImpl implements VendeeService {

    private final VendeeMapper vendeeMapper;
    private final AbodeMapper abodeMapper;
    private final VendeeRepository vendeeRepository;
    private final AbodeRepository abodeRepository;

    public VendeeServiceImpl(
            VendeeMapper vendeeMapper,
            AbodeMapper abodeMapper,
            VendeeRepository vendeeRepository,
            AbodeRepository abodeRepository) {
        this.vendeeMapper = vendeeMapper;
        this.abodeMapper = abodeMapper;
        this.vendeeRepository = vendeeRepository;
        this.abodeRepository = abodeRepository;
    }

    @Override
    public VendeeDtoResponse createVendee(VendeeDtoRequest request) {
        // The parent entity (Vendee) should be saved first.
        // Then, the child entity (Abode).

        // Gets the Abode data from the Request
        Abode _abd =
                abodeMapper.dtoCreateToEntity(request);
        // Gets the Vendee data, which was stored in the database
        Vendee _vnd =
                // Stores Vendee data in the database, but first without Abode
                vendeeRepository.save(
                        // Gets the Vendee data from the Request
                        vendeeMapper.dtoCreateToEntity(request)
                );
        // Adds the Vendee entity data to the Abode entity
        _abd.setVendee(_vnd);
        // Gets the Abode entity data when saving it to the database
        Abode abode = abodeRepository.save(_abd);
        // Adds the Abode entity data to the Vendee entity
        _vnd.setAbode(abode);

        return (_vnd != null)
                ? new VendeeDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(VendeeDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .vendee(VendeeModel.getModel(_vnd))
                .build()
                : new VendeeDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(VendeeDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public VendeeDtoResponse getAllVendees() {
        List<Vendee> list = vendeeRepository.findAll();
        if (!list.isEmpty()) {
            List<VendeeModel> _list = new ArrayList<>();
            for (Vendee vendee : list)
                _list.add(VendeeModel.getModel(vendee));
            return new VendeeDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(VendeeDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .vendeeList(_list)
                    .build();
        } else
            return new VendeeDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(VendeeDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .vendeeList(Collections.emptyList())
                    .build();
    }

    @Override
    public VendeeDtoResponse getAllVendeesByAbode() {
        List<Abode> list = abodeRepository.findAll();
        if (!list.isEmpty()) {
            List<VendeeModel> _list = new ArrayList<>();
            for (Abode abode : list)
                _list.add(VendeeModel.getModel(abode));
            return new VendeeDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(VendeeDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .vendeeList(_list)
                    .build();
        } else
            return new VendeeDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(VendeeDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .vendeeList(Collections.emptyList())
                    .build();
    }

    @Override
    public VendeeDtoResponse getVendeeById(Long id) {
        Optional<Vendee> optional = vendeeRepository.findById(id);
        return (optional.isPresent())
                ? new VendeeDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(VendeeDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .vendee(VendeeModel.getModel(optional.get()))
                .build()
                : new VendeeDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(VendeeDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public VendeeDtoResponse updateVendeeById(Long id, VendeeDtoRequest request) {
        Optional<Vendee> vendeeOptional = vendeeRepository.findById(id);
        Optional<Abode> abodeOptional = abodeRepository.findById(id);

        Vendee vendee = (vendeeOptional.isPresent() & abodeOptional.isPresent())
                ? vendeeRepository.saveAndFlush(
                vendeeMapper.dtoUpdateByIdToEntity(
                        id, request,
                        vendeeOptional.get(),
                        abodeOptional.get()))
                : null;

        return (vendee != null)
                ? new VendeeDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(VendeeDtoResponse
                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                        .formatted(id))
                .vendee(VendeeModel.getModel(vendee))
                .build()
                : new VendeeDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(VendeeDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public VendeeDtoResponse deleteVendeeById(Long id) {
        if (vendeeRepository.findById(id).isPresent()) {
            vendeeRepository.deleteById(id);
            return new VendeeDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(VendeeDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new VendeeDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(VendeeDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
