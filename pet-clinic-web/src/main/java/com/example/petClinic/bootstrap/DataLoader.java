package com.example.petClinic.bootstrap;

import com.example.petClinic.model.*;
import com.example.petClinic.services.OwnerService;
import com.example.petClinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        //Create Pet Type
        PetType dogType = new PetType("Dog");
        PetType catType = new PetType("Cat");
        PetType fishType = new PetType("Fish");

        Pet kimDog = new Pet();
        kimDog.setName("Jordan");
        kimDog.setBirthDate(LocalDate.now());
        kimDog.setPetType(dogType);

        Owner owner1 = new Owner();
        owner1.setFirstName("Kim");
        owner1.setLastName("Kapoor");
        owner1.setAddress("Block 24/2 Vasant Vihar");
        owner1.setCity("Delhi");
        owner1.getPets().add(kimDog);

        //save Pet , Owner And Pet Type
        ownerService.save(owner1);


        Pet gleenFish = new Pet();
        gleenFish.setName("katie");
        gleenFish.setBirthDate(LocalDate.now());
        gleenFish.setPetType(fishType);

        Owner owner2 = new Owner();
        owner2.setFirstName("Glenn");
        owner2.setLastName("Anand");
        owner2.setAddress("b-34 Nehru Road");
        owner2.setCity("Delhi");
        owner2.getPets().add(gleenFish);

        //save Pet , Owner And Pet Type
        ownerService.save(owner2);

        System.out.println("Owners Added Successfully!");

        Speciality surgerySpeciality = new Speciality("Surgery");
        Speciality skinSpeciality = new Speciality("Skin therapist");

        Vet vet1 = new Vet();
        vet1.setFirstName("Philip");
        vet1.setLastName("Mehra");
        vet1.getSpecialitySet().add(surgerySpeciality);

        //Save Vet And Speciality
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Tachanka");
        vet2.setLastName("Porter");
        vet2.getSpecialitySet().add(surgerySpeciality);
        vet2.getSpecialitySet().add(skinSpeciality);

        //Save Vet And Speciality
        vetService.save(vet2);

        System.out.println("Vets Added Successfully!");
    }

}
