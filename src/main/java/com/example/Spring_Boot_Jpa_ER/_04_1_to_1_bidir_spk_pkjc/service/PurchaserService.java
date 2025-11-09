package com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.service;

import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Jpa_ER._04_1_to_1_bidir_spk_pkjc.dto.PurchaserDtoResponse;

public interface PurchaserService {
    PurchaserDtoResponse createPurchaser(PurchaserDtoRequest request);
    PurchaserDtoResponse getAllPurchasers();
    PurchaserDtoResponse getAllPurchasersByHabitation();
    PurchaserDtoResponse getPurchaserById(Long id);
    PurchaserDtoResponse updatePurchaserById(Long id, PurchaserDtoRequest request);
    PurchaserDtoResponse deletePurchaserById(Long id);
}
