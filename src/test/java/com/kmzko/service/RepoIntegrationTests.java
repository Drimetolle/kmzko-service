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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoIntegrationTests {
    @Autowired
    QuestionnaireRepo questionnaireRepo;

    @Autowired
    RateRepo rateRepo;

    @Before
    public void upContext() {
        questionnaireRepo.deleteAll();
        rateRepo.deleteAll();
    }

    @Test
    public void checkOneInsertToQuestionnaireRepo() throws Exception {
        Questionnaire res = new Questionnaire();
        res.setName("quest");
        res.setType(ConveyorType.TAPE.toString());

        List<Questionnaire> listQ = new ArrayList(Arrays.asList(new Questionnaire[]{res}));

        Rate rate1 = new Rate("wqe1", "ggsd1");
        rate1.setQuestionnaire(res);
        Rate rate2 = new Rate("wqe2", "ggsd2");
        rate2.setQuestionnaire(res);
        Rate rate3 = new Rate("wqe3", "ggsd3");
        rate3.setQuestionnaire(res);
        List<Rate> list = new ArrayList(Arrays.asList(new Rate[]{rate1, rate2, rate3}));

        rateRepo.saveAll(list);
        questionnaireRepo.save(res);

        List<Questionnaire> questionnaireList = questionnaireRepo.findByType(ConveyorType.TAPE.toString());
        Questionnaire questionnaire = questionnaireList.get(questionnaireList.size() - 1);

        assertThat(questionnaire.getRateList()).isNotNull();
        assertThat(questionnaireList.size()).isEqualTo(1);
        assertThat(questionnaire.getRateList().get(0).getName()).isEqualTo(rate1.getName());
        assertThat(questionnaire.getName()).isEqualTo(res.getName());
        assertThat(questionnaire.getType()).isEqualTo(res.getType());
    }
}
