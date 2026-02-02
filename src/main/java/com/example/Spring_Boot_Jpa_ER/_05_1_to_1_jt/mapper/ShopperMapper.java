package com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.mapper;

import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.entity.Place;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.entity.Shopper;
import org.springframework.stereotype.Component;

@Component
public class ShopperMapper {

    public Shopper dtoCreateToEntity(ShopperDtoRequest request) {
        return Shopper.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .place(getPlace(request))
                .build();
    }

    private Place getPlace(ShopperDtoRequest request) {
        return Place.builder()
                .city(request.city())
                .street(request.street())
                .building(request.building())
                .apartment(request.apartment())
                .build();
    }

    public Shopper dtoUpdateByIdToEntity(Long id,
                                         ShopperDtoRequest request,
                                         Shopper shopperToUpdate,
                                         Place placeToUpdate) {
        shopperToUpdate.setId(id);
        placeToUpdate.setId(id);
        shopperToUpdate.setFirstName(request.firstName());
        shopperToUpdate.setLastName(request.lastName());
        shopperToUpdate.setEmail(request.email());
        shopperToUpdate.setPlace(
                getPlaceToUpdate(request, placeToUpdate));
        return shopperToUpdate;
    }

    private Place getPlaceToUpdate(ShopperDtoRequest request,
                                   Place placeToUpdate) {
        placeToUpdate.setCity(request.city());
        placeToUpdate.setStreet(request.street());
        placeToUpdate.setBuilding(request.building());
        placeToUpdate.setApartment(request.apartment());
        return placeToUpdate;
    }
}
