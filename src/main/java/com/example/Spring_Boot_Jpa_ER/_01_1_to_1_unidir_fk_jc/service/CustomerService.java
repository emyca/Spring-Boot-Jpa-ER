package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoResponse;

public interface CustomerService {
    CustomerDtoResponse create(CustomerDtoRequest request);
    CustomerDtoResponse getAll();
    CustomerDtoResponse getById(Long id);
    CustomerDtoResponse updateById(Long id, CustomerDtoRequest request);
    CustomerDtoResponse deleteById(Long id);
}
