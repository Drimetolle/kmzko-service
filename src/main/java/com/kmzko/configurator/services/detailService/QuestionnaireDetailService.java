package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
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
        return questionnaireRepo.findLatestRecord(type.getValue()).get();
    }

    @Override
    public List<Questionnaire> getAll() {
        return questionnaireRepo.findAll();
    }

    @Override
    public Optional<Questionnaire> getById(long id) {
        return questionnaireRepo.findById(id);
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
