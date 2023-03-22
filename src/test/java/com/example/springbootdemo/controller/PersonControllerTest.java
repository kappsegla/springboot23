package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Person;
import com.example.springbootdemo.repository.PersonRepository;
import com.example.springbootdemo.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
@ContextConfiguration(classes = {SecurityConfig.class, PersonController.class,GlobalControllerAdvice.class})
class PersonControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

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

        when(repository.findAll()).thenReturn(List.of(person1, person2));

        mockMvc.perform(get("/api/persons").with(user("user")))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void getPersonByIdReturns200OK() throws Exception {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("Kalle");

        String expectedJson = """
                {"id":1,"name":"Kalle"}""";

        when(repository.findById(1L)).thenReturn(Optional.of(person1));

        var result = mockMvc.perform(get("/api/persons/1")).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedJson);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getPersonByIdReturns200OKWithJsonBody() throws Exception {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("Kalle");

        when(repository.findById(1L)).thenReturn(Optional.of(person1));

        var result = mockMvc.perform(get("/api/persons/1"))
                .andExpect(status().isOk())
                .andExpect(ResponseBodyMatchers.responseBody().containsObjectAsJson(person1, Person.class));
    }


    @Test
    @WithMockUser(roles="ADMIN")
    void getPersonByIdThatDoesNotExistsReturns404() throws Exception {
        mockMvc.perform(get("/api/persons/2"))
                .andExpect(status().isNotFound());
    }
}
