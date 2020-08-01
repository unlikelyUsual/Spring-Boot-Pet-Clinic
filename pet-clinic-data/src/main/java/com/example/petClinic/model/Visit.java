package com.example.petClinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@Getter
@Setter
public class Visit extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate visitDate;

    private String description;

    private Pet pet;
}
