package com.example.petClinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "speciality")
public class Speciality extends BaseEntity {

    @Column(name = "description")
    private String description;

    public Speciality(String description) {
        this.description = description;
    }


}
