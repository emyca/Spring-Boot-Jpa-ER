package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.service;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.dto.VendeeDtoResponse;

public interface VendeeService {
    VendeeDtoResponse createVendee(VendeeDtoRequest request);
    VendeeDtoResponse getAllVendees();
    VendeeDtoResponse getAllVendeesByAbode();
    VendeeDtoResponse getVendeeById(Long id);
    VendeeDtoResponse updateVendeeById(Long id, VendeeDtoRequest request);
    VendeeDtoResponse deleteVendeeById(Long id);
}
