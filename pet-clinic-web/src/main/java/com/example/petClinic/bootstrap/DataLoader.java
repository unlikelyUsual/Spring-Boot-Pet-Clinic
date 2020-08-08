package com.example.petClinic.bootstrap;

import com.example.petClinic.model.*;
import com.example.petClinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final VisitService visitService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, VisitService visitService, PetService petService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.visitService = visitService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        //Create Pet Type
        PetType dogType = petTypeService.save(new PetType("Dog"));
        //PetType catType = petTypeService.save(new PetType("Cat"));
        PetType fishType = petTypeService.save(new PetType("Fish"));


        Owner owner1 = new Owner();
        owner1.setFirstName("Kim");
        owner1.setLastName("Kapoor");
        owner1.setAddress("Block 24/2 Vasant Vihar");
        owner1.setCity("Delhi");


        Pet kimDog = new Pet();
        kimDog.setName("Jordan");
        kimDog.setBirthDate(LocalDate.now());
        kimDog.setPetType(dogType);
        kimDog.setOwner(owner1);

        owner1.getPets().add(kimDog);


        //save Pet , Owner And Pet Type
        ownerService.save(owner1);

        Visit kimDogVisit = new Visit();
        kimDogVisit.setDescription("Need Surgery");
        kimDogVisit.setVisitDate(LocalDate.now());
        kimDogVisit.setPet(kimDog);

        //Save Visit
        visitService.save(kimDogVisit);


        Owner owner2 = new Owner();
        owner2.setFirstName("Glenn");
        owner2.setLastName("Anand");
        owner2.setAddress("b-34 Nehru Road");
        owner2.setCity("Delhi");


        Pet gleenFish = new Pet();
        gleenFish.setName("katie");
        gleenFish.setBirthDate(LocalDate.now());
        gleenFish.setPetType(fishType);
        gleenFish.setOwner(owner2);

        owner2.getPets().add(gleenFish);

        //save Pet , Owner And Pet Type
        ownerService.save(owner2);

        System.out.println("Owners Added Successfully!");

        Speciality surgerySpeciality = specialityService.save(new Speciality("Surgery"));
        Speciality skinSpeciality = specialityService.save(new Speciality("Skin therapist"));

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
