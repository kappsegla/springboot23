package com.example.springbootdemo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private UserCredentialsRepository repository;

    public DatabaseUserDetailsService(UserCredentialsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials userCredentials = repository.findByName(username);
        if (userCredentials == null)
            throw new UsernameNotFoundException("username not found");

        return new User(userCredentials.getName(),userCredentials.getPassword(), Set.of());
    }
}
