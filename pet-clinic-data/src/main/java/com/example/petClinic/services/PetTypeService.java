package com.example.petClinic.services;

import com.example.petClinic.model.PetType;

public interface PetTypeService extends CrudService<PetType,Long> {

    PetType findByName(String name);

}
