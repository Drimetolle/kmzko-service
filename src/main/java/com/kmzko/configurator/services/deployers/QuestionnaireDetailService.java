package com.kmzko.configurator.services.deployers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.Questionnaire;
import com.kmzko.configurator.repositories.QuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireDetailService implements DetailService<Questionnaire> {
    private final QuestionnaireRepo questionnaireRepo;

    public QuestionnaireDetailService(QuestionnaireRepo questionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
    }

    public Questionnaire getLastRevisionQuestionnaire(ConveyorType type) {
        return questionnaireRepo.findLatestRecord(type.getValue());
    }

    @Override
    public List<Questionnaire> getAll() {
        return questionnaireRepo.findAll();
    }

    @Override
    public Questionnaire getById(long id) {
        Optional<Questionnaire> item = questionnaireRepo.findById(id);

        return item.orElseGet(Questionnaire::new);
    }

    @Override
    public Questionnaire save(Questionnaire questionnaire) {
        return questionnaireRepo.save(questionnaire);
    }

    @Override
    public Questionnaire update(Questionnaire questionnaire) {
        return null;
    }

    @Override
    public boolean delete(Questionnaire questionnaire) {
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