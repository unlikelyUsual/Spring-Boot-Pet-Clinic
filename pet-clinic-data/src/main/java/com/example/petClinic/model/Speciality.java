package com.example.petClinic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Speciality extends BaseEntity {

    private String description;

    public Speciality(String description) {
        this.description = description;
    }
}
