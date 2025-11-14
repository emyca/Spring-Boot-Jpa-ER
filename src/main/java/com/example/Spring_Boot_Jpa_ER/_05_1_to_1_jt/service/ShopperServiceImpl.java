package com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.service;

import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto.ShopperDtoResponse;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.entity.Place;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.entity.Shopper;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.mapper.ShopperMapper;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.model.ShopperModel;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.repository.PlaceRepository;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("ShopperServiceImpl")
public class ShopperServiceImpl implements ShopperService {

    @Autowired
    private ShopperMapper shopperMapper;
    @Autowired
    private ShopperRepository shopperRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public ShopperDtoResponse createShopper(ShopperDtoRequest request) {
        Shopper shopper = shopperRepository
                .saveAndFlush(shopperMapper
                        .dtoCreateToEntity(request));
        return (shopper != null)
                ? new ShopperDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(ShopperDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .shopper(ShopperModel.getModel(shopper))
                .build()
                : new ShopperDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(ShopperDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public ShopperDtoResponse getAllShoppers() {
        List<Shopper> list = shopperRepository.findAll();
        if (!list.isEmpty()) {
            List<ShopperModel> _list = new ArrayList<>();
            for (Shopper shopper : list)
                _list.add(ShopperModel.getModel(shopper));
            return new ShopperDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ShopperDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .shopperList(_list)
                    .build();
        } else
            return new ShopperDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(ShopperDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .shopperList(Collections.emptyList())
                    .build();
    }

    @Override
    public ShopperDtoResponse getAllShoppersByPlace() {
        List<Place> list = placeRepository.findAll();
        if (!list.isEmpty()) {
            List<ShopperModel> _list = new ArrayList<>();
            for (Place place : list)
                _list.add(ShopperModel.getModel(place));
            return new ShopperDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ShopperDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .shopperList(_list)
                    .build();
        } else
            return new ShopperDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(ShopperDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .shopperList(Collections.emptyList())
                    .build();
    }

    @Override
    public ShopperDtoResponse getShopperById(Long id) {
        Shopper shopper = shopperRepository.findById(id)
                .orElse(null);
        return (shopper != null)
                ? new ShopperDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(ShopperDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .shopper(ShopperModel.getModel(shopper))
                .build()
                : new ShopperDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ShopperDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public ShopperDtoResponse updateShopperById(Long id, ShopperDtoRequest request) {
        Optional<Shopper> shopperOptional = shopperRepository.findById(id);
        Optional<Place> placeOptional = placeRepository.findById(id);
        Shopper shopper = (shopperOptional.isPresent() & placeOptional.isPresent())
                ? shopperRepository.saveAndFlush(
                shopperMapper.dtoUpdateByIdToEntity(
                        id, request,
                        shopperOptional.get(),
                        placeOptional.get()))
                : null;
        return (shopper != null)
                ? new ShopperDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(ShopperDtoResponse
                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                        .formatted(id))
                .shopper(ShopperModel.getModel(shopper))
                .build()
                : new ShopperDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ShopperDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public ShopperDtoResponse deleteShopperById(Long id) {
        if (shopperRepository.findById(id).isPresent()) {
            shopperRepository.deleteById(id);
            return new ShopperDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ShopperDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new ShopperDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ShopperDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

}
