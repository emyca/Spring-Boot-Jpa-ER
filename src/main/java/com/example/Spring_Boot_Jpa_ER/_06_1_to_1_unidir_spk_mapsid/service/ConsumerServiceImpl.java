package com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.service;

import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoResponse;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.entity.Consumer;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.entity.Domicile;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.mapper.ConsumerMapper;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.model.ConsumerModel;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.repository.ConsumerRepository;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.repository.DomicileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("ConsumerServiceImpl")
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerMapper consumerMapper;
    private final ConsumerRepository consumerRepository;
    private final DomicileRepository domicileRepository;

    public ConsumerServiceImpl(
            ConsumerMapper consumerMapper,
            ConsumerRepository consumerRepository,
            DomicileRepository domicileRepository) {
        this.consumerMapper = consumerMapper;
        this.consumerRepository = consumerRepository;
        this.domicileRepository = domicileRepository;
    }

    @Override
    public ConsumerDtoResponse createConsumer(ConsumerDtoRequest request) {
        Consumer consumer = consumerRepository
                .saveAndFlush(consumerMapper
                        .dtoCreateToEntity(request));
        return (consumer != null)
                ? new ConsumerDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(ConsumerDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .consumer(ConsumerModel.getModel(consumer))
                .build()
                : new ConsumerDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(ConsumerDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public ConsumerDtoResponse getAllConsumers() {
        List<Consumer> list = consumerRepository.findAll();
        if (!list.isEmpty()) {
            List<ConsumerModel> _list = new ArrayList<>();
            for (Consumer consumer : list)
                _list.add(ConsumerModel.getModel(consumer));
            return new ConsumerDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ConsumerDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .consumerList(_list)
                    .build();
        } else
            return new ConsumerDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(ConsumerDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .consumerList(Collections.emptyList())
                    .build();
    }

    @Override
    public ConsumerDtoResponse getConsumerById(Long id) {
        Consumer consumer = consumerRepository.findById(id)
                .orElse(null);
        return (consumer != null)
                ? new ConsumerDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(ConsumerDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .consumer(ConsumerModel.getModel(consumer))
                .build()
                : new ConsumerDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ConsumerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public ConsumerDtoResponse updateConsumerById(Long id, ConsumerDtoRequest request) {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);
        Optional<Domicile> domicileOptional = domicileRepository.findById(id);
        Consumer consumer = (consumerOptional.isPresent() & domicileOptional.isPresent()) ?
                consumerRepository.saveAndFlush(
                        consumerMapper.dtoUpdateByIdToEntity(
                                id, request,
                                consumerOptional.get(),
                                domicileOptional.get())) : null;
        return (consumer != null)
                ? new ConsumerDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(ConsumerDtoResponse
                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                        .formatted(id))
                .consumer(ConsumerModel.getModel(consumer))
                .build()
                : new ConsumerDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ConsumerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public ConsumerDtoResponse deleteConsumerById(Long id) {
        if (consumerRepository.findById(id).isPresent()) {
            consumerRepository.deleteById(id);
            return new ConsumerDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(ConsumerDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new ConsumerDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ConsumerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

}
