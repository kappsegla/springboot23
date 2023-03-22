package com.example.springbootdemo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {


    public OidcUser getCurrentUser(){
        return  (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
