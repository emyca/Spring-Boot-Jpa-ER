package com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.mapper;

import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.entity.Place;
import com.example.Spring_Boot_Jpa_ER._05_1_to_1_jt.entity.Shopper;
import org.springframework.stereotype.Component;

@Component
public class ShopperMapper {

    public Shopper dtoCreateToEntity(ShopperDtoRequest request) {
        Shopper shopper = new Shopper();
        shopper.setId(request.id());
        shopper.setFirstName(request.firstName());
        shopper.setLastName(request.lastName());
        shopper.setEmail(request.email());
        Place place = new PlaceMapper()
                .dtoCreateToEntity(request);
        shopper.setPlace(place);
        return shopper;
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
        Place place = new PlaceMapper()
                .dtoUpdateToEntity(request, placeToUpdate);
        shopperToUpdate.setPlace(place);
        return shopperToUpdate;
    }
}
