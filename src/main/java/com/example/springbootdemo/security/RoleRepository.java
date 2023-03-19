package com.example.springbootdemo.security;

import org.springframework.data.repository.ListCrudRepository;

public interface RoleRepository extends ListCrudRepository<Role, Long> {
    Role findByName(String name);
}
