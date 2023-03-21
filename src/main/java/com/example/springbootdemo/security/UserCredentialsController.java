package com.example.springbootdemo.security;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCredentialsController {

    @PostMapping("/register")
    public void createUser(){
        //Todo: Save a new user
        //Don't forget to hash password before storing
    }
}
