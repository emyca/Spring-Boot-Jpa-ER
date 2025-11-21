package com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.service;

import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoResponse;

public interface ConsumerService {
    ConsumerDtoResponse createConsumer(ConsumerDtoRequest request);
    ConsumerDtoResponse getAllConsumers();
    ConsumerDtoResponse getConsumerById(Long id);
    ConsumerDtoResponse updateConsumerById(Long id, ConsumerDtoRequest request);
    ConsumerDtoResponse deleteConsumerById(Long id);
}
