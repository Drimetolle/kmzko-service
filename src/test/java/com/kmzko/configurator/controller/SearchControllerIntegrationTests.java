package com.kmzko.configurator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.services.kmzko.api.Request1CAPI;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    private static Request1CAPI request1CAPI = Mockito.mock(Request1CAPI.class);

    private final String  baseUrl = "/api/search";
    private final static String json = "[{\"id\":0,\"name\":\"Конвейер ленточный\",\"type\":\"tape\",\"nodes\":[{\"id\":0,\"name\":\"Лента\",\"details\":[{\"id\":0,\"name\":\"Конвейерная лента\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"2\",\"mark\":\"tape-width\",\"type\":\"meter\"},{\"id\":0,\"name\":\"Длина ленты\",\"value\":\"50\",\"mark\":\"tape-length\",\"type\":\"meter\"}]}]},{\"id\":1,\"name\":\"Приводная станция\",\"details\":[{\"id\":0,\"name\":\"Приводной барабан\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"530\",\"mark\":\"\",\"type\":\"meter\"}]},{\"id\":1,\"name\":\"Барабан отклоняющий\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"325\",\"mark\":\"\",\"type\":\"meter\"}]}]}]},{\"id\":1,\"name\":\"Конвейер ленточный 2\",\"type\": \"tape\",\"nodes\":[{\"id\":1,\"name\":\"Лента\",\"details\":[{\"id\":1,\"name\":\"Конвейерная лента\",\"characteristics\":[{\"id\":1,\"name\":\"Ширина ленты\",\"value\":\"2\",\"mark\":\"tape-width\",\"type\":\"meter\"},{\"id\":1,\"name\":\"Длина ленты\",\"value\":\"50\",\"mark\":\"tape-length\",\"type\":\"meter\"}]}]}]}]";

    @BeforeClass
    public static void setUpClass() {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> map = new ArrayList<>();
        try {
            map = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Mockito.when(request1CAPI.getNearConveyors(any()))
                .thenReturn(map);
    }

    @Test
    public void emptyParams() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/conveyors").contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(content().string("[]"))
                .andExpect(status().isOk());
    }

    @Test
    public void notValidParams() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/conveyors").param("saf", "1243").contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(content().string("[]"))
                .andExpect(status().isOk());
    }

    @Test
    public void matchConveyor() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/conveyors")
                .param("tape-length", "50")
                .param("tape-width", "2")
                .contentType(MediaType.ALL).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(content().string(containsString("tape-width")))
                .andExpect(content().string(containsString("tape-length")))
                .andExpect(content().string(containsString("meter")))
                .andExpect(status().isOk());
    }
}
