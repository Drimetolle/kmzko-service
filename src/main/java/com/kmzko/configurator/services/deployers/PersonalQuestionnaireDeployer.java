package com.kmzko.configurator.services.deployers;

import com.kmzko.configurator.entity.PersonalQuestionnaire;
import com.kmzko.configurator.repositories.PersonalQuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonalQuestionnaireDeployer implements Deployer<PersonalQuestionnaire> {
    private final PersonalQuestionnaireRepo questionnaireRepo;

    public PersonalQuestionnaireDeployer(PersonalQuestionnaireRepo questionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
    }

    @Override
    public PersonalQuestionnaire save(PersonalQuestionnaire questionnaire) {
        return questionnaireRepo.save(questionnaire);
    }

    @Override
    public boolean delete(PersonalQuestionnaire questionnaire) {
        try {
            questionnaireRepo.delete(questionnaire);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        try {
            questionnaireRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}
