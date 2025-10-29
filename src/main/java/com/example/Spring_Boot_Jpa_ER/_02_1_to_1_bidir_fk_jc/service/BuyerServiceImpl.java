package com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoResponse;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.entity.Address;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.entity.Buyer;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.mapper.BuyerMapper;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.model.BuyerModel;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.repository.AddressRepository;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("BuyerServiceImpl")
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerMapper buyerMapper;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public BuyerDtoResponse createBuyer(BuyerDtoRequest request) {
        Buyer buyer = buyerRepository
                .saveAndFlush(buyerMapper.dtoCreateToEntity(request));
        return (buyer != null)
                ? new BuyerDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(BuyerDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .buyer(BuyerModel.getModel(buyer))
                .build()
                : new BuyerDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(BuyerDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public BuyerDtoResponse getAllBuyers() {
        List<Buyer> list = buyerRepository.findAll();
        if (!list.isEmpty()) {
            List<BuyerModel> _list = new ArrayList<>();
            for (Buyer buyer : list)
                _list.add(BuyerModel.getModel(buyer));
            return new BuyerDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(BuyerDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .buyerList(_list)
                    .build();
        } else
            return new BuyerDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(BuyerDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .buyerList(Collections.emptyList())
                    .build();
    }

    @Override
    public BuyerDtoResponse getAllBuyersByAddress() {
        List<Address> list = addressRepository.findAll();
        if (!list.isEmpty()) {
            List<BuyerModel> _list = new ArrayList<>();
            for (Address address : list)
                _list.add(BuyerModel.getModel(address));
            return new BuyerDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(BuyerDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .buyerList(_list)
                    .build();
        } else
            return new BuyerDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(BuyerDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .buyerList(Collections.emptyList())
                    .build();
    }

    @Override
    public BuyerDtoResponse getBuyerById(Long id) {
        Buyer buyer = buyerRepository.findById(id)
                .orElse(null);
        return (buyer != null)
                ? new BuyerDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(BuyerDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .buyer(BuyerModel.getModel(buyer))
                .build()
                : new BuyerDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(BuyerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public BuyerDtoResponse updateBuyerById(Long id,
                                            BuyerDtoRequest request) {
        Optional<Buyer> buyerOptional = buyerRepository.findById(id);
        Optional<Address> addressOptional = addressRepository.findById(id);

        Buyer buyer = (buyerOptional.isPresent() & addressOptional.isPresent())
                ? buyerRepository.saveAndFlush(
                buyerMapper.dtoUpdateByIdToEntity(
                        id, request,
                        buyerOptional.get(),
                        addressOptional.get()))
                : null;

        return (buyer != null)
                ? new BuyerDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(BuyerDtoResponse
                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                        .formatted(id))
                .buyer(BuyerModel.getModel(buyer))
                .build()
                : new BuyerDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(BuyerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public BuyerDtoResponse deleteBuyerById(Long id) {
        if (buyerRepository.findById(id).isPresent()) {
            buyerRepository.deleteById(id);
            return new BuyerDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(BuyerDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new BuyerDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(BuyerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
