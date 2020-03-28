package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.dto.QuestionnaireDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireMapper extends AbstractMapper<Questionnaire, QuestionnaireDto> {
    private final ModelMapper mapper;

    public QuestionnaireMapper(ModelMapper mapper) {
        super(Questionnaire.class, QuestionnaireDto.class);
        this.mapper = mapper;
    }
}
