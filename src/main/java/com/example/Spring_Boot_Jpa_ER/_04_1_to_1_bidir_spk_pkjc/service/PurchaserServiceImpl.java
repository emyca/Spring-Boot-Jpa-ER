package com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.service;

import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto.PurchaserDtoResponse;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.entity.Habitation;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.entity.Purchaser;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.mapper.PurchaserMapper;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.model.PurchaserModel;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.repository.HabitationRepository;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.repository.PurchaserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("PurchaserServiceImpl")
public class PurchaserServiceImpl implements PurchaserService {

    private final PurchaserMapper purchaserMapper;
    private final PurchaserRepository purchaserRepository;
    private final HabitationRepository habitationRepository;

    public PurchaserServiceImpl(
            PurchaserMapper purchaserMapper,
            PurchaserRepository purchaserRepository,
            HabitationRepository habitationRepository) {
        this.purchaserMapper = purchaserMapper;
        this.purchaserRepository = purchaserRepository;
        this.habitationRepository = habitationRepository;
    }

    @Override
    public PurchaserDtoResponse createPurchaser(PurchaserDtoRequest request) {
        Purchaser purchaser = purchaserRepository
                .saveAndFlush(purchaserMapper.dtoCreateToEntity(request));
        return (purchaser != null)
                ? new PurchaserDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(PurchaserDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .purchaser(PurchaserModel.getModel(purchaser))
                .build()
                : new PurchaserDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(PurchaserDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public PurchaserDtoResponse getAllPurchasers() {
        List<Purchaser> list = purchaserRepository.findAll();
        if (!list.isEmpty()) {
            List<PurchaserModel> _list = new ArrayList<>();
            for (Purchaser purchaser : list)
                _list.add(PurchaserModel.getModel(purchaser));
            return new PurchaserDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(PurchaserDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .purchaserList(_list)
                    .build();
        } else
            return new PurchaserDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(PurchaserDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .purchaserList(Collections.emptyList())
                    .build();
    }

    @Override
    public PurchaserDtoResponse getAllPurchasersByHabitation() {
        List<Habitation> list = habitationRepository.findAll();
        if (!list.isEmpty()) {
            List<PurchaserModel> _list = new ArrayList<>();
            for (Habitation habitation : list)
                _list.add(PurchaserModel.getModel(habitation));
            return new PurchaserDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(PurchaserDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .purchaserList(_list)
                    .build();
        } else
            return new PurchaserDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(PurchaserDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .purchaserList(Collections.emptyList())
                    .build();
    }

    @Override
    public PurchaserDtoResponse getPurchaserById(Long id) {
        Purchaser purchaser = purchaserRepository.findById(id)
                .orElse(null);
        return (purchaser != null)
                ? new PurchaserDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(PurchaserDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .purchaser(PurchaserModel.getModel(purchaser))
                .build()
                : new PurchaserDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(PurchaserDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public PurchaserDtoResponse updatePurchaserById(Long id, PurchaserDtoRequest request) {
        Optional<Purchaser> purchaserOptional = purchaserRepository.findById(id);
        Optional<Habitation> habitationOptional = habitationRepository.findById(id);

        Purchaser purchaser =
                (purchaserOptional.isPresent() & habitationOptional.isPresent())
                        ? purchaserRepository.saveAndFlush(
                        purchaserMapper.dtoUpdateByIdToEntity(
                                id, request,
                                purchaserOptional.get(),
                                habitationOptional.get()))
                        : null;

        return (purchaser != null)
                ? new PurchaserDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(PurchaserDtoResponse
                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                        .formatted(id))
                .purchaser(PurchaserModel.getModel(purchaser))
                .build()
                : new PurchaserDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(PurchaserDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public PurchaserDtoResponse deletePurchaserById(Long id) {
        if (purchaserRepository.findById(id).isPresent()) {
            purchaserRepository.deleteById(id);
            return new PurchaserDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(PurchaserDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new PurchaserDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(PurchaserDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
