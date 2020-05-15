package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.questionnaire.PersonalQuestionnaireDto;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonalQuestionnaireMapper extends AbstractMapper<PersonalQuestionnaire, PersonalQuestionnaireDto> {
    private final ModelMapper mapper;
    private final PersonalRateMapper rateMapper;

    PersonalQuestionnaireMapper(ModelMapper mapper, PersonalRateMapper rateMapper) {
        super(PersonalQuestionnaire.class, PersonalQuestionnaireDto.class);
        this.mapper = mapper;
        this.rateMapper = rateMapper;
    }
}
