package com.kmzko.service;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.domains.Rate;
import com.kmzko.service.repositories.QuestionnaireRepo;
import com.kmzko.service.repositories.RateRepo;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @Autowired
    RateRepo rateRepo;

    @Test
    public void EnumReturnValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/questionnaire").contentType(MediaType.ALL))
                .andDo(print())
//                .andExpect(content().string(containsString(ConveyorType.TAPE.getView())))
                .andExpect(status().isOk());
    }

    @Test
    public void testReturnValue() throws Exception {
        Questionnaire res = new Questionnaire();
        res.setName("quest");
        res.setType(ConveyorType.TAPE.toString());

        List<Questionnaire> listQ = new ArrayList(Arrays.asList(new Questionnaire[]{res}));

        Rate rate1 = new Rate("wqe1", "");
        rate1.setQuestionnaire(res);
        Rate rate2 = new Rate("wqe2", "");
        rate2.setQuestionnaire(res);
        Rate rate3 = new Rate("wqe3", "");
        rate3.setQuestionnaire(res);
        List<Rate> list = new ArrayList(Arrays.asList(new Rate[]{rate1, rate2, rate3}));

        rateRepo.saveAll(list);
        questionnaireRepo.save(res);

        mvc.perform(MockMvcRequestBuilders.get("/api/questionnaire/tape"))
                .andDo(print())
                .andExpect(content().string(containsString("quest")))
                .andExpect(content().string(containsString("wqe1")))
                .andExpect(status().isOk());
    }

    @Test
    public void badType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/questionnaire/khgaohguhenjignji"))
                .andExpect(content().string(containsString("")))
                .andExpect(status().isNoContent());
    }
}
