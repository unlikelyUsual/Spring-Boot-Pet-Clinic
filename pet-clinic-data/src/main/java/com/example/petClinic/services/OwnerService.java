package com.example.petClinic.services;

import com.example.petClinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

 }
