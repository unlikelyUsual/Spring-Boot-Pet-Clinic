package com.example.petClinic.controllers;

import com.example.petClinic.model.Owner;
import com.example.petClinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/find")
    String getFindOwner(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/search")
    String searchOwner(Owner owner, BindingResult result, Model model){

        if(owner.getLastName() == null) owner.setLastName("");

        List<Owner> ownerList = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if(ownerList == null || ownerList.size() == 0){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if(ownerList.size() == 1){
            return  "redirect:/owner/view/" + ownerList.get(0).getId();
        }
        else {
            model.addAttribute("owners",ownerService.findAll());
            return "owners/index";
        }
    }

    @GetMapping("/view/{id}")
    String viewOwnerDetail(@PathVariable("id") Long id,  Model model) {
        model.addAttribute("owner",ownerService.findById(id));
        return "owners/viewOwner";
    }

    @GetMapping("/create")
    String createOwnerPage(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/ownerForm";
    }

    @GetMapping("/modify/{id}")
    String modifyOwnerPage(@PathVariable("id") Long id , Model model) {
        model.addAttribute("owner",ownerService.findById(id));
        return "owners/ownerForm";
    }

    @PostMapping("/addOrUpdate")
    String saveOrUpdateOwner(Owner owner , BindingResult result , Model model){
        if(result.hasErrors()) return "owners/ownerForm";
        Owner saveOwner = ownerService.save(owner);
        return  "redirect:/owner/view/" + saveOwner.getId();
    }

}
