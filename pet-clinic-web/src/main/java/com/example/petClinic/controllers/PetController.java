package com.example.petClinic.controllers;

import com.example.petClinic.model.Owner;
import com.example.petClinic.model.Pet;
import com.example.petClinic.model.PetType;
import com.example.petClinic.services.OwnerService;
import com.example.petClinic.services.PetService;
import com.example.petClinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pet")
public class PetController {

    private final PetService petService;

    private final PetTypeService petTypeService;

    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @GetMapping("/ownerId/{ownerId}/create")
    String getAddPet( @PathVariable("ownerId") Long ownerId , Model model) {
        Owner owner = ownerService.findById(ownerId);
        if(owner == null) throw  new RuntimeException("Owner Not Found");
        model.addAttribute("pet", Pet.builder().owner(owner).build());
        model.addAttribute("typeMap", petTypeService.findAll());
        return "pets/petForm";
    }

    @GetMapping("/modify/{id}")
    String getEditPet(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pet",petService.findById(id));
        model.addAttribute("typeMap", petTypeService.findAll());
        return "pets/petForm";
    }

    @PostMapping("/addOrUpdate")
    String saveOrUpdate(Pet pet,Model model){
        if(pet.getOwner() == null) throw new RuntimeException("Owner Not Found");
        PetType petType = petTypeService.findByName(pet.getPetType().getName());
        Owner owner = ownerService.findById(pet.getOwner().getId());
        pet.setPetType(petType);
        if(pet.getId() == null){
            pet.setOwner(owner);
            owner.getPets().add(pet);
        }
        else{
            Pet savedPet = petService.save(pet);
        }
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owner/view/" + savedOwner.getId();
    }

}
