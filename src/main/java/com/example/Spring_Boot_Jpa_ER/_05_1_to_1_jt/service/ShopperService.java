package com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.service;

import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto.ShopperDtoResponse;

public interface ShopperService {
    ShopperDtoResponse createShopper(ShopperDtoRequest request);
    ShopperDtoResponse getAllShoppers();
    ShopperDtoResponse getAllShoppersByPlace();
    ShopperDtoResponse getShopperById(Long id);
    ShopperDtoResponse updateShopperById(Long id, ShopperDtoRequest request);
    ShopperDtoResponse deleteShopperById(Long id);
}
