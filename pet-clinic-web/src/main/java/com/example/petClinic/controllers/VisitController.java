package com.example.petClinic.controllers;

import com.example.petClinic.model.Pet;
import com.example.petClinic.model.Visit;
import com.example.petClinic.services.PetService;
import com.example.petClinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("visit")
public class VisitController {

    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @GetMapping("/petId/{petId}/create")
    String getAddVisitPage(@PathVariable("petId") Long petId, Model model) {
        model.addAttribute("pet",petService.findById(petId));
        model.addAttribute("visit", new Visit());
        return "visits/visitForm";
    }

    @PostMapping("/petId/{petId}/create")
    String saveVisit(@PathVariable("petId") Long petId ,@ModelAttribute("visit")  Visit visit){
        if(petId == null) throw new RuntimeException("Pet Not Found");
        Pet pet = petService.findById(petId);
        visit.setPet(pet);
        pet.getVisitSet().add(visit);
        Pet savedPet = petService.save(pet);
        return "redirect:/owner/view/" + savedPet.getOwner().getId();
    }
}
