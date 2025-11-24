package com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.model;

import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Abode;
import com.example.Spring_Boot_Jpa_ER._07_1_to_1_bidir_spk_mapsid.entity.Vendee;
import lombok.Data;

@Data
public class VendeeModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Abode abode;

    public static VendeeModel getModel(Vendee vendee) {
        VendeeModel model = new VendeeModel();
        model.setId(vendee.getId());
        model.setFirstName(vendee.getFirstName());
        model.setLastName(vendee.getLastName());
        model.setEmail(vendee.getEmail());
        Abode _abode = new Abode();
        _abode.setId(vendee.getAbode().getId());
        _abode.setCity(vendee.getAbode().getCity());
        _abode.setStreet(vendee.getAbode().getStreet());
        _abode.setBuilding(vendee.getAbode().getBuilding());
        _abode.setApartment(vendee.getAbode().getApartment());
        model.setAbode(_abode);
        return model;
    }

    public static VendeeModel getModel(Abode abode) {
        VendeeModel model = new VendeeModel();
        model.setId(abode.getVendee().getId());
        model.setFirstName(abode.getVendee().getFirstName());
        model.setLastName(abode.getVendee().getLastName());
        model.setEmail(abode.getVendee().getEmail());
        Abode _abode = new Abode();
        _abode.setId(abode.getId());
        _abode.setCity(abode.getCity());
        _abode.setStreet(abode.getStreet());
        _abode.setBuilding(abode.getBuilding());
        _abode.setApartment(abode.getApartment());
        model.setAbode(_abode);
        return model;
    }
}
