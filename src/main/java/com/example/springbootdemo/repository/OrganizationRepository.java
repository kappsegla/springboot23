package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.Organization;
import com.example.springbootdemo.projection.OrgName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrganizationRepository extends ListCrudRepository<Organization, Long> {

    //@Query("from Organization o join fetch o.persons p")
    //@EntityGraph(attributePaths = {"persons"})
    @EntityGraph(value = "Organization.persons")
    List<Organization> findAll();

    @EntityGraph(attributePaths = {"persons"})
    List<OrgName> findNamesBy();
}
