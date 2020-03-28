package com.kmzko.configurator.services.deployers;

import com.kmzko.configurator.entity.user.PersonalQuestionnaire;
import com.kmzko.configurator.repositories.PersonalQuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalQuestionnaireDetailService implements DetailService<PersonalQuestionnaire> {
    private final PersonalQuestionnaireRepo questionnaireRepo;

    public PersonalQuestionnaireDetailService(PersonalQuestionnaireRepo questionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
    }

    @Override
    public List<PersonalQuestionnaire> getAll() {
        return questionnaireRepo.findAll();
    }

    @Override
    public PersonalQuestionnaire getById(long id) {
        Optional<PersonalQuestionnaire> item = questionnaireRepo.findById(id);

        return item.orElseGet(PersonalQuestionnaire::new);
    }

    @Override
    public PersonalQuestionnaire save(PersonalQuestionnaire questionnaire) {
        return questionnaireRepo.save(questionnaire);
    }

    @Override
    public PersonalQuestionnaire update(PersonalQuestionnaire personalQuestionnaire) {
        return null;
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
