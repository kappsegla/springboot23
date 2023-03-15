package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Person;
import com.example.springbootdemo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonRepository repository;

    @Test
    void getPersonsShouldReturnListOfAllPersons() throws Exception {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("Kalle");
        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("Rut");

        when(repository.findAll()).thenReturn(List.of(person1,person2));

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonByIdReturns200OK() throws Exception {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("Kalle");

        when(repository.findById(1L)).thenReturn(Optional.of(person1));

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonByIdThatDoesNotExistsReturns404() throws Exception {
        mockMvc.perform(get("/persons/2"))
                .andExpect(status().isNotFound());
    }

}
