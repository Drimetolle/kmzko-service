package com.kmzko.configurator.services.deployers;

import com.kmzko.configurator.entity.PersonalQuestionnaire;
import com.kmzko.configurator.repositories.PersonalQuestionnaireRepo;
import org.springframework.stereotype.Service;

@Service
public class PersonalQuestionnaireDeployer implements Deployer<PersonalQuestionnaire> {
    private final PersonalQuestionnaireRepo questionnaireRepo;

    public PersonalQuestionnaireDeployer(PersonalQuestionnaireRepo questionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
    }

    @Override
    public PersonalQuestionnaire save(PersonalQuestionnaire o) {
        return null;
    }

    @Override
    public boolean delete(PersonalQuestionnaire o) {
        return false;
    }

    @Override
    public boolean deleteById(long o) {
        return false;
    }
}
