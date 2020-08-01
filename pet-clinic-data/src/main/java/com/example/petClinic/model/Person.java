package com.example.petClinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
public class Person extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
