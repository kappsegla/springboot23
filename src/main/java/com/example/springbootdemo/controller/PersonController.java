package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Person;
import com.example.springbootdemo.repository.PersonRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository repo;

    public PersonController(PersonRepository personRepository) {
        repo = personRepository;
    }

    @GetMapping("/{id}")
    Person getAName(@PathVariable long id) {
        return repo.findById(id).orElseThrow();
                //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    List<Person> getNames() {
        return repo.findAll();
    }

    @PostMapping
    ResponseEntity<Void> addName(@RequestBody Person person) {
        String name = person.getName();
        if (name == null || name.isEmpty())
            throw new IllegalStateException();
        var newEntity = repo.save(person);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEntity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/lang")
    String preferredLanguage(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lang) {
        return lang;
    }
}
