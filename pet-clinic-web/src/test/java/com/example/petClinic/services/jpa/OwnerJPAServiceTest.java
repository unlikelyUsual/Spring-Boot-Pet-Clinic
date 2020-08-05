package com.example.petClinic.services.jpa;

import com.example.petClinic.model.Owner;
import com.example.petClinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJPAServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJPAService ownerJPAService;

    private Owner savedOwner;

    private final String lastName = "Smith";

    private final Long ownerId = 1L;

    @BeforeEach
    void setUp() {
        savedOwner = Owner.builder().id(ownerId).lastName(lastName).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(Optional.of(savedOwner));
        Owner owner = ownerJPAService.findByLastName(lastName);
        assertEquals(lastName,owner.getLastName());
    }

    @Test
    void findAll() {
        Set<Owner> owner = new HashSet<>();
        owner.add(Owner.builder().id(2L).build());
        owner.add(Owner.builder().id(3L).build());
        when(ownerRepository.findAll()).thenReturn(owner);
        assertEquals(2,ownerJPAService.findAll().size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(savedOwner));
        Owner owner = ownerJPAService.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void save() {
        when(ownerRepository.save(any(Owner.class))).thenReturn(savedOwner);
        Owner owner = ownerJPAService.save(savedOwner);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void delete() {
        ownerJPAService.delete(savedOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerJPAService.deleteById(ownerId);
        verify(ownerRepository).deleteById(any());
    }
}