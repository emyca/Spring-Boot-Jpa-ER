package com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.mapper;

import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.entity.Consumer;
import com.example.Spring_Boot_Jpa_ER._06_1_to_1_unidir_spk_mapsid.entity.Domicile;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMapper {

    public Consumer dtoCreateToEntity(ConsumerDtoRequest request) {
        Consumer consumer = new Consumer();
        consumer.setId(request.id());
        consumer.setFirstName(request.firstName());
        consumer.setLastName(request.lastName());
        consumer.setEmail(request.email());
        Domicile domicile = new DomicileMapper()
                .dtoCreateToEntity(request);
        consumer.setDomicile(domicile);
        return consumer;
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
        Domicile residence = new DomicileMapper()
                .dtoUpdateToEntity(request, domicileToUpdate);
        consumerToUpdate.setDomicile(residence);
        return consumerToUpdate;
    }
}
