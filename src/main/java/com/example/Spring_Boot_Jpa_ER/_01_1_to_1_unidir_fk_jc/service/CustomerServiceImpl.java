package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoResponse;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.entity.Customer;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.entity.Location;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.mapper.CustomerMapper;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.model.CustomerModel;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.repository.CustomerRepository;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final LocationRepository locationRepository;

    public CustomerServiceImpl(
            CustomerMapper customerMapper,
            CustomerRepository customerRepository,
            LocationRepository locationRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public CustomerDtoResponse createCustomer(CustomerDtoRequest request) {
        Customer customer = customerRepository
                .saveAndFlush(customerMapper.dtoCreateToEntity(request));
        return (customer != null)
                ? new CustomerDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(CustomerDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .customer(new CustomerModel().getModel(customer))
                .build()
                : new CustomerDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(CustomerDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public CustomerDtoResponse getAllCustomers() {
        List<Customer> list = customerRepository.findAll();
        if (!list.isEmpty()) {
            List<CustomerModel> _list = new ArrayList<>();
            for (Customer customer : list)
                _list.add(new CustomerModel().getModel(customer));
            return new CustomerDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CustomerDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .customerList(_list)
                    .build();
        } else {
            return new CustomerDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(CustomerDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .customerList(Collections.emptyList())
                    .build();
        }
    }

    @Override
    public CustomerDtoResponse getCustomerById(Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        return (optional.isPresent())
                ? new CustomerDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(CustomerDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .customer(new CustomerModel().getModel(optional.get()))
                .build()
                : new CustomerDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(CustomerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public CustomerDtoResponse updateCustomerById(Long id, CustomerDtoRequest request) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (customerOptional.isPresent() & locationOptional.isPresent()) {
            Customer customer = customerRepository.saveAndFlush(
                    customerMapper.dtoUpdateByIdToEntity(
                            id, request,
                            customerOptional.get(),
                            locationOptional.get()));
            return new CustomerDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CustomerDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .customer(new CustomerModel().getModel(customer))
                    .build();
        } else
            return new CustomerDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(CustomerDtoResponse
                            .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
    }

    @Override
    public CustomerDtoResponse deleteCustomerById(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
            return new CustomerDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CustomerDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new CustomerDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(CustomerDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
