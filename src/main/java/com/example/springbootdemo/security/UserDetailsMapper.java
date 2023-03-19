package com.example.springbootdemo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {
    public UserDetails toUserDetails(UserCredentials user) {
        return new User(user.getName(), user.getPassword(), user.getGrantedAuthorities());
    }
}
