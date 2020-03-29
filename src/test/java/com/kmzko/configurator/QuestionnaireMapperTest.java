package com.kmzko.configurator;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.dto.QuestionnaireDto;
import com.kmzko.configurator.dto.RateDto;
import com.kmzko.configurator.mappers.QuestionnaireMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionnaireMapperTest {
    @Autowired
    private QuestionnaireMapper mapper;

    @Test
    public void defaultBehaviorEntityToDto() {
        List<Rate> rateList = new ArrayList<Rate>();
        Questionnaire questionnaire = new Questionnaire("Q1", ConveyorType.TAPE, rateList);
        questionnaire.setId(Long.valueOf(1));

        QuestionnaireDto personalQuestionnaire = mapper.toDto(questionnaire);

        assertThat(personalQuestionnaire).isNotNull();
        assertThat(personalQuestionnaire.getId()).isEqualTo(questionnaire.getId());
        assertThat(personalQuestionnaire.getName()).isEqualTo(questionnaire.getName());
        assertThat(personalQuestionnaire.getType()).isEqualTo(questionnaire.getType().toString());
        assertThat(personalQuestionnaire.getRateList()).isEqualTo(rateList);
    }

    @Test
    public void defaultBehaviorDtoToEntity() {
        List<RateDto> rateList = new ArrayList<RateDto>();
        QuestionnaireDto questionnaireDto = new QuestionnaireDto("Q1", "TAPE", rateList);
        questionnaireDto.setId(Long.valueOf(1));

        Questionnaire questionnaire = mapper.toEntity(questionnaireDto);

        assertThat(questionnaire).isNotNull();
        assertThat(questionnaireDto.getId()).isEqualTo(questionnaire.getId());
        assertThat(questionnaireDto.getName()).isEqualTo(questionnaire.getName());
        assertThat(questionnaireDto.getType()).isEqualTo(questionnaire.getType().toString());
        assertThat(questionnaireDto.getRateList()).isEqualTo(rateList);
    }

    @Test
    public void checkCaseType() {
        List<RateDto> rateList = new ArrayList<RateDto>();
        QuestionnaireDto questionnaireDto = new QuestionnaireDto("Q1", "TaPe", rateList);
        questionnaireDto.setId(Long.valueOf(1));

        Questionnaire questionnaire = mapper.toEntity(questionnaireDto);

        assertThat(questionnaire).isNotNull();
        assertThat(questionnaireDto.getId()).isEqualTo(questionnaire.getId());
        assertThat(questionnaireDto.getName()).isEqualTo(questionnaire.getName());
        assertThat(questionnaireDto.getType().toUpperCase()).isEqualTo(questionnaire.getType().toString());
        assertThat(questionnaireDto.getRateList()).isEqualTo(rateList);
    }
}