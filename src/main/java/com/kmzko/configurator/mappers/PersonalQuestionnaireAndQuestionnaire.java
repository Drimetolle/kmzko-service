package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import org.springframework.stereotype.Component;

@Component
public class PersonalQuestionnaireAndQuestionnaire extends AbstractMapper<PersonalQuestionnaire, QuestionnaireDto> {
    PersonalQuestionnaireAndQuestionnaire() {
        super(PersonalQuestionnaire.class, QuestionnaireDto.class);
    }
}
