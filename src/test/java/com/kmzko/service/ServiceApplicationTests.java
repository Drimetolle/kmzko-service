package com.kmzko.service;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.domains.Rate;
import com.kmzko.service.repositories.QuestionnaireRepo;
import com.kmzko.service.services.GenerateQuestionnaire;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceApplicationTests {
    private GenerateQuestionnaire generateQuestionnaire = Mockito.mock(GenerateQuestionnaire.class);

    @Test
    void contextLoads() {
        List<Rate> list = new ArrayList(Arrays.asList(new Rate[]{new Rate(), new Rate(), new Rate()}));
        Questionnaire res = new Questionnaire(list, ConveyorType.TAPE.toString());
        Mockito.when(generateQuestionnaire.sad(any())).thenReturn(res);

        assertThat(generateQuestionnaire.sad(ConveyorType.TAPE)).isNotNull();
        assertThat(generateQuestionnaire.sad(ConveyorType.TAPE)).isEqualTo(res);
    }

}
