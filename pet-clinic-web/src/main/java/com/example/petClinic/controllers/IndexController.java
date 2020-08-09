package com.example.petClinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"","/","/index","/home"})
    String getIndexPage(){
        return "index";
    }
}
