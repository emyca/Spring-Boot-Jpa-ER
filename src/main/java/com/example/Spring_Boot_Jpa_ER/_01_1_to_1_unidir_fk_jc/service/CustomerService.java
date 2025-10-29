package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoResponse;

public interface CustomerService {
    CustomerDtoResponse createCustomer(CustomerDtoRequest request);
    CustomerDtoResponse getAllCustomers();
    CustomerDtoResponse getCustomerById(Long id);
    CustomerDtoResponse updateCustomerById(Long id, CustomerDtoRequest request);
    CustomerDtoResponse deleteCustomerById(Long id);
}
