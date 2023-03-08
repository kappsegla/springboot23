package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.Organization;
import org.springframework.data.repository.ListCrudRepository;

public interface OrganizationRepository extends ListCrudRepository<Organization,Long> {
}
