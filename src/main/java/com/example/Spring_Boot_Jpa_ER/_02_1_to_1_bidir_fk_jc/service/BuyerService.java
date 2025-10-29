package com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoResponse;

public interface BuyerService {
    BuyerDtoResponse createBuyer(BuyerDtoRequest request);
    BuyerDtoResponse getAllBuyers();
    BuyerDtoResponse getAllBuyersByAddress();
    BuyerDtoResponse getBuyerById(Long id);
    BuyerDtoResponse updateBuyerById(Long id, BuyerDtoRequest request);
    BuyerDtoResponse deleteBuyerById(Long id);
}
