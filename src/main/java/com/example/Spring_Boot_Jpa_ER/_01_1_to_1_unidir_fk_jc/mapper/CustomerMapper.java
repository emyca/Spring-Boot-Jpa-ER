package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.mapper;

import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.entity.Customer;
import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer dtoCreateToEntity(CustomerDtoRequest request) {
        Customer customer = new Customer();
        customer.setId(request.id());
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setLocation(getLocation(request));
        return customer;
    }

    private Location getLocation(CustomerDtoRequest request) {
        Location location = new Location();
        location.setCity(request.city());
        location.setStreet(request.street());
        location.setBuilding(request.building());
        location.setApartment(request.apartment());
        return location;
    }

    public Customer dtoUpdateByIdToEntity(Long id,
                                          CustomerDtoRequest request,
                                          Customer customerToUpdate,
                                          Location locationToUpdate) {
        customerToUpdate.setId(id);
        locationToUpdate.setId(id);
        customerToUpdate.setFirstName(request.firstName());
        customerToUpdate.setLastName(request.lastName());
        customerToUpdate.setEmail(request.email());
        customerToUpdate.setLocation(getLocationToUpdate(request, locationToUpdate));
        return customerToUpdate;
    }

    private Location getLocationToUpdate(CustomerDtoRequest request,
                                      Location locationToUpdate) {
        locationToUpdate.setCity(request.city());
        locationToUpdate.setStreet(request.street());
        locationToUpdate.setBuilding(request.building());
        locationToUpdate.setApartment(request.apartment());
        return locationToUpdate;
    }
}
