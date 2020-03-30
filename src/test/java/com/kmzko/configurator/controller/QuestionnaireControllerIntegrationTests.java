package com.kmzko.configurator.controller;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.repositories.QuestionnaireRepo;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionnaireControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    QuestionnaireRepo questionnaireRepo;

    @Test
    public void EnumReturnValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/questionnaires").contentType(MediaType.ALL))
                .andDo(print())
                .andExpect(content().string(containsString(ConveyorType.TAPE.getView())))
                .andExpect(status().isOk());
    }

    @Test
    public void testReturnValue() throws Exception {
        Rate rate1 = new Rate("wqe1", "", "");
        Rate rate2 = new Rate("wqe2", "", "");
        Rate rate3 = new Rate("wqe3", "", "");
        List<Rate> list = new ArrayList(Arrays.asList(new Rate[]{rate1, rate2, rate3}));

        Questionnaire res = new Questionnaire();
        res.setName("quest");
        res.setRateList(list);
        res.setType(ConveyorType.TAPE);

        List<Questionnaire> listQ = new ArrayList(Arrays.asList(new Questionnaire[]{res}));


        questionnaireRepo.save(res);

        mvc.perform(MockMvcRequestBuilders.get("/api/questionnaires/tape"))
                .andDo(print())
                .andExpect(content().string(containsString("quest")))
                .andExpect(content().string(containsString("wqe1")))
                .andExpect(status().isOk());
    }

    @Test
    public void badType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/questionnaires/khgaohguhenjignji"))
                .andExpect(content().string(containsString("")))
                .andExpect(status().isNoContent());
    }
}
