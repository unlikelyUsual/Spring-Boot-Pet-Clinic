package com.example.petClinic.controllers;

import com.example.petClinic.model.Owner;
import com.example.petClinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"","/","/index"})
    String getIndexPage(Model model){
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("/view/{id}")
    String viewOwnerDetail(@PathVariable("id") Long id,  Model model) {
        model.addAttribute("owner",ownerService.findById(id));
        return "owners/viewOwner";
    }

    @GetMapping("/create")
    String createOwnerPage(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/ownerForm";
    }

    @GetMapping("/modify/{id}")
    String modifyOwnerPage(@PathVariable("id") Long id , Model model) {
        model.addAttribute("owner",ownerService.findById(id));
        return "owners/ownerForm";
    }

}
