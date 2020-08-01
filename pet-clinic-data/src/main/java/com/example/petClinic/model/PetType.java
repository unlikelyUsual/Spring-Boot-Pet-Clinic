package com.example.petClinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


@Setter
@Getter
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
