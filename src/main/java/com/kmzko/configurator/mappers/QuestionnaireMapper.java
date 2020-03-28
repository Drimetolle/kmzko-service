package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.dto.QuestionnaireDto;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireMapper extends AbstractMapper<Questionnaire, QuestionnaireDto> {
    public QuestionnaireMapper() {
        super(Questionnaire.class, QuestionnaireDto.class);
    }
}
