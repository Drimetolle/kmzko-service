package com.kmzko.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.domains.Rate;
import com.kmzko.service.repositories.QuestionnaireRepo;
import com.kmzko.service.repositories.RateRepo;
import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeployControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    QuestionnaireRepo questionnaireRepo;

    @Autowired
    RateRepo rateRepo;

    @Before
    public void setUp() {
        questionnaireRepo.deleteAll();
        rateRepo.deleteAll();
    }

    @After
    public void check() {

    }

    @Test
    public void deleteQuestionnaireIfNotExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/deploy/questionnaire/123").contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteQuestionnaireIfNotExistAndNotValidQueryParam() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/deploy/questionnaire/dfhas").contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteQuestionnaireIfExist() throws Exception {
        Rate rate1 = new Rate("wqe1", "", "");
        Rate rate2 = new Rate("wqe2", "", "");
        Rate rate3 = new Rate("wqe3", "", "");
        List<Rate> list = new ArrayList(Arrays.asList(new Rate[]{rate1, rate2, rate3}));

        Questionnaire res = new Questionnaire();
        res.setName("quest");
        res.setRateList(list);
        res.setType(ConveyorType.TAPE.toString());

        List<Questionnaire> listQ = new ArrayList(Arrays.asList(new Questionnaire[]{res}));

        questionnaireRepo.save(res);

        Questionnaire q = questionnaireRepo.findAll().iterator().next();

        mvc.perform(MockMvcRequestBuilders.delete("/api/deploy/questionnaire/{id}", q.getId())
                .contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(questionnaireRepo.count()).isEqualTo(0);
        assertThat(rateRepo.count()).isEqualTo(0);
    }

    @Test
    public void postNewQuestionnaire() throws Exception {
        Rate rate1 = new Rate("wqe1", "", "");
        Rate rate2 = new Rate("wqe2", "", "");
        Rate rate3 = new Rate("wqe3", "", "");
        List<Rate> list = new ArrayList(Arrays.asList(new Rate[]{rate1, rate2, rate3}));

        Questionnaire res = new Questionnaire();
        res.setName("quest");
        res.setType(ConveyorType.TAPE.toString());
        res.setRateList(list);


        mvc.perform(MockMvcRequestBuilders.post("/api/deploy/questionnaire")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(res)))
                .andDo(print())
                //.andExpect(header().string("Location", "http://localhost/api/questionnaire/"))
                .andExpect(status().isCreated());

        assertThat(questionnaireRepo.count()).isEqualTo(1);
        assertThat(rateRepo.count()).isEqualTo(3);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
