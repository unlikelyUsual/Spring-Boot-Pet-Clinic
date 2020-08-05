package com.example.petClinic.services.map;

import com.example.petClinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private Long ownerId = 1L;
    private String lastName = "Sam";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(),new PetTypeMapService());
        ownerMapService.map.put(ownerId, Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(ownerId).build();
        assertEquals(ownerId,ownerMapService.save(owner).getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
       Owner owner =  ownerMapService.findByLastName(lastName);
       assertEquals(lastName,owner.getLastName());
    }

}