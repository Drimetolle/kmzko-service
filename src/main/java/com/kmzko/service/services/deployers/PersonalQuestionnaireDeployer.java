package com.kmzko.service.services.deployers;

import com.kmzko.service.entity.PersonalQuestionnaire;
import org.springframework.stereotype.Service;

@Service
public class PersonalQuestionnaireDeployer implements Deployer<PersonalQuestionnaire> {
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
