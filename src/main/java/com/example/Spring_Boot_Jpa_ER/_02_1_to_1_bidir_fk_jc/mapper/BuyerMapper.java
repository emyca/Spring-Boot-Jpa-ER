package com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.mapper;

import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.entity.Address;
import com.example.Spring_Boot_Jpa_ER._02_1_to_1_bidir_fk_jc.entity.Buyer;
import org.springframework.stereotype.Component;

@Component
public class BuyerMapper {

    public Buyer dtoCreateToEntity(BuyerDtoRequest request) {
        Buyer buyer = new Buyer();
        buyer.setId(request.id());
        buyer.setFirstName(request.firstName());
        buyer.setLastName(request.lastName());
        buyer.setEmail(request.email());
        buyer.setAddress(getAddress(request));
        return buyer;
    }

    private Address getAddress(BuyerDtoRequest request) {
        Address address = new Address();
        address.setCity(request.city());
        address.setStreet(request.street());
        address.setBuilding(request.building());
        address.setApartment(request.apartment());
        return address;
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
