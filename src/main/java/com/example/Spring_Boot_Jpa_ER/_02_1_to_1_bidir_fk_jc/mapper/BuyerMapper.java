package com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.mapper;

import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.entity.Address;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.entity.Buyer;
import org.springframework.stereotype.Component;

@Component
public class BuyerMapper {

    public Buyer dtoCreateToEntity(BuyerDtoRequest request) {
        return Buyer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(getAddress(request))
                .build();
    }

    private Address getAddress(BuyerDtoRequest request) {
        return Address.builder()
                .city(request.city())
                .street(request.street())
                .building(request.building())
                .apartment(request.apartment())
                .build();
    }

    public Buyer dtoUpdateByIdToEntity(Long id,
                                       BuyerDtoRequest request,
                                       Buyer buyerToUpdate,
                                       Address addressToUpdate) {
        buyerToUpdate.setId(id);
        addressToUpdate.setId(id);
        buyerToUpdate.setFirstName(request.firstName());
        buyerToUpdate.setLastName(request.lastName());
        buyerToUpdate.setEmail(request.email());
        buyerToUpdate.setAddress(
                getAddressToUpdate(request, addressToUpdate));
        return buyerToUpdate;
    }

    private Address getAddressToUpdate(BuyerDtoRequest request,
                                       Address addressToUpdate) {
        addressToUpdate.setCity(request.city());
        addressToUpdate.setStreet(request.street());
        addressToUpdate.setBuilding(request.building());
        addressToUpdate.setApartment(request.apartment());
        return addressToUpdate;
    }
}
