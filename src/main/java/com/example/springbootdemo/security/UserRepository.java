package com.example.springbootdemo.security;

import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserCredentials, Long> {

    UserCredentials findByName(String name);
}
