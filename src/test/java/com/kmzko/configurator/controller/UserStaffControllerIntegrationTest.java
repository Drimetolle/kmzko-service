package com.kmzko.configurator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.entity.user.PersonalConveyor;
import com.kmzko.configurator.entity.user.PersonalQuestionnaire;
import com.kmzko.configurator.entity.user.Role;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.repositories.PersonalConveyorRepo;
import com.kmzko.configurator.repositories.UserRepo;
import com.kmzko.configurator.services.kmzko.api.Request1CAPI;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;

import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserStaffControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonalConveyorRepo repo;

    @Autowired
    private static UserRepo userRepo;

    private final String  baseUrl = "/api/user";
    private final static String json = "{\"id\":0,\"name\":\"Конвейер ленточный\", \"type\":\"tape\",\"nodes\":[{\"id\":0,\"name\":\"Лента\",\"details\":[{\"id\":0,\"name\":\"Конвейерная лента\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"2\",\"mark\":\"tape-width\",\"type\":\"meter\"},{\"id\":0,\"name\":\"Длина ленты\",\"value\":\"50\",\"mark\":\"tape-length\",\"type\":\"meter\"}]}]},{\"id\":1,\"name\":\"Приводная станция\",\"details\":[{\"id\":0,\"name\":\"Приводной барабан\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"530\",\"mark\":\"\",\"type\":\"meter\"}]},{\"id\":1,\"name\":\"Барабан отклоняющий\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"325\",\"mark\":\"\",\"type\":\"meter\"}]}]}]}";

    private static final User user = new User("ex@gmail.com", "qwf", "123", new HashSet<>(),  new HashSet<>(),  new HashSet<>());

//    @BeforeClass
//    public static void upContext() {
//        userRepo.save(user);
//    }

    @Before
    public void setUp() {
        repo.deleteAll();
    }

//    @Test
//    public void post() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post(baseUrl + "/conveyors").contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andDo(print())
//                .andExpect(content().string(containsString("Конвейерная лента")))
//                .andExpect(status().isOk());
//        assertThat(repo.count()).isEqualTo(1);
//    }

    @Test
    public void putIfNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/conveyors").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(repo.count()).isEqualTo(0);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}