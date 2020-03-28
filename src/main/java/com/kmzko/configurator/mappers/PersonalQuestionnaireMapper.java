package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.PersonalQuestionnaireDto;
import com.kmzko.configurator.entity.user.PersonalQuestionnaire;
import org.springframework.stereotype.Component;

@Component
public class PersonalQuestionnaireMapper extends AbstractMapper<PersonalQuestionnaire, PersonalQuestionnaireDto> {
    public PersonalQuestionnaireMapper() {
        super(PersonalQuestionnaire.class, PersonalQuestionnaireDto.class);
    }
}
