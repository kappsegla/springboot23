package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Person;
import com.example.springbootdemo.repository.PersonRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository repo;

    public PersonController(PersonRepository personRepository) {
        repo = personRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity<Person> getAName(@PathVariable long id) {
        return ResponseEntity.of(repo.findById(id));
    }

    @GetMapping
    List<Person> getNames() {
        return repo.findAll();
    }

    @PostMapping
    void addName(@RequestBody Person person) {
        String name = person.getName();
        if (name == null || name.isEmpty())
            throw new IllegalStateException();
        repo.save(person);
    }

    @GetMapping("/lang")
    String preferredLanguage(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lang) {
        return lang;
    }
}
