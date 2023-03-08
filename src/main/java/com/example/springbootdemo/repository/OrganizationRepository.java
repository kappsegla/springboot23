package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrganizationRepository extends ListCrudRepository<Organization, Long> {
}
