package com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.mapper;

import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.entity.Consumer;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.entity.Domicile;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMapper {

    public Consumer dtoCreateToEntity(ConsumerDtoRequest request) {
        return Consumer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .domicile(getDomicile(request))
                .build();
    }

    private Domicile getDomicile(ConsumerDtoRequest request) {
        return Domicile.builder()
                .city(request.city())
                .street(request.street())
                .building(request.building())
                .apartment(request.apartment())
                .build();
    }

    public Consumer dtoUpdateByIdToEntity(Long id,
                                          ConsumerDtoRequest request,
                                          Consumer consumerToUpdate,
                                          Domicile domicileToUpdate) {
        consumerToUpdate.setId(id);
        domicileToUpdate.setId(id);
        consumerToUpdate.setFirstName(request.firstName());
        consumerToUpdate.setLastName(request.lastName());
        consumerToUpdate.setEmail(request.email());
        consumerToUpdate.setDomicile(
                getDomicileToUpdate(request, domicileToUpdate));
        return consumerToUpdate;
    }

    private Domicile getDomicileToUpdate(ConsumerDtoRequest request,
                                         Domicile domicileToUpdate) {
        domicileToUpdate.setCity(request.city());
        domicileToUpdate.setStreet(request.street());
        domicileToUpdate.setBuilding(request.building());
        domicileToUpdate.setApartment(request.apartment());
        return domicileToUpdate;
    }
}
