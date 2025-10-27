package com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.mapper;

import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address dtoCreateToEntity(BuyerDtoRequest request) {
        Address address = new Address();
        address.setCity(request.city());
        address.setStreet(request.street());
        address.setBuilding(request.building());
        address.setApartment(request.apartment());
        return address;
    }

    public Address dtoUpdateToEntity(BuyerDtoRequest request,
                                     Address addressToUpdate) {
        addressToUpdate.setCity(request.city());
        addressToUpdate.setStreet(request.street());
        addressToUpdate.setBuilding(request.building());
        addressToUpdate.setApartment(request.apartment());
        return addressToUpdate;
    }
}
