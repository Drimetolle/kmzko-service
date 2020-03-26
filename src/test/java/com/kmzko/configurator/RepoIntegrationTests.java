package com.kmzko.configurator;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.Questionnaire;
import com.kmzko.configurator.domains.Rate;
import com.kmzko.configurator.repositories.QuestionnaireRepo;
import com.kmzko.configurator.repositories.RateRepo;
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
        Rate rate1 = new Rate("wqe1", "ggsd1", "");
        Rate rate2 = new Rate("wqe2", "ggsd2", "");
        Rate rate3 = new Rate("wqe3", "ggsd3", "");
        List<Rate> list = new ArrayList(Arrays.asList(new Rate[]{rate1, rate2, rate3}));

        Questionnaire res = new Questionnaire();
        res.setName("quest");
        res.setRateList(list);
        res.setType(ConveyorType.TAPE.toString());

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
