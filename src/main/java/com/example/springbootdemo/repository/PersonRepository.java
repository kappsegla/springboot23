package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.Person;
import org.springframework.data.repository.ListCrudRepository;

public interface PersonRepository extends ListCrudRepository<Person,Long> {

}
