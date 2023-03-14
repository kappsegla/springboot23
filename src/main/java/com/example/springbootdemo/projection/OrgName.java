package com.example.springbootdemo.projection;

import com.example.springbootdemo.entity.Person;

import java.util.List;

public interface OrgName {
    String getName();

    List<Person> getPersons();
}
