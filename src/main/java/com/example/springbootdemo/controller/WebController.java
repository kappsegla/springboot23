package com.example.springbootdemo.controller;


import com.example.springbootdemo.repository.PersonRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WebController {

    private final PersonRepository personRepository;

    public WebController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }


    @GetMapping("/showPersons")
    String persons(Model model, UserInfoService userinfo){
        model.addAttribute("message", "Hello World");
        model.addAttribute("allPersons", personRepository.findAll());
        model.addAttribute("profilePic", userinfo.getCurrentUser().getPicture());
        return "persons";
    }
}
