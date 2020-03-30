package com.kmzko.configurator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.dto.UserDto;
import com.kmzko.configurator.mappers.UserMapper;
import com.kmzko.configurator.repositories.UserRepo;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepo repo;
    @Autowired
    private UserMapper mapper;

    private final String  baseUrl = "/api/join";

    @BeforeEach
    public void setUp() {
        repo.deleteAll();
    }

    @Test
    void createUser() throws Exception {
        UserDto userDto = new UserDto("fasf", "asfafs", "qwrfqw", new HashSet<>());
        mvc.perform(MockMvcRequestBuilders.post(baseUrl).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto)))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(repo.count()).isEqualTo(1);
        assertThat(repo.findAll().get(0).getEmail()).isEqualTo("fasf");
    }

    @Test
    void createEmptyUser() throws Exception {
        UserDto userDto = new UserDto();
        mvc.perform(MockMvcRequestBuilders.post(baseUrl).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        assertThat(repo.count()).isEqualTo(0);
    }

    @Test
    void createZeroLengthFieldUser() throws Exception {
        UserDto userDto = new UserDto("", "", "", new HashSet<>());
        mvc.perform(MockMvcRequestBuilders.post(baseUrl).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        assertThat(repo.count()).isEqualTo(0);
    }

    @Test
    void createUserIfEmailExist() throws Exception {
        UserDto userDto = new UserDto("1", "1", "1", new HashSet<>());
        UserDto secondUserDto = new UserDto("1", "2", "2", new HashSet<>());
        repo.save(mapper.toEntity(userDto));

        mvc.perform(MockMvcRequestBuilders.post(baseUrl).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(secondUserDto)))
                .andExpect(content().string(containsString("error")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}