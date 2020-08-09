package com.example.petClinic.controllers;

import com.example.petClinic.model.Vet;
import com.example.petClinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService vetService;

    @InjectMocks
    VetController vetController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
       mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void getIndexPage() throws Exception {
        Vet vet = new Vet();
        vet.setId(1L);
        Vet vet2 = new Vet();
        vet2.setId(2L);
        Set<Vet> vets = new HashSet<>();
        vets.add(vet);
        vets.add(vet2);
        when(vetService.findAll()).thenReturn(vets);

        mockMvc.perform(get("/vets/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets",hasSize(2)));
    }
}