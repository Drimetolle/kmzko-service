package com.kmzko.service.services.deployers;

import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.repositories.QuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireDeployer implements Deployer<Questionnaire> {
    private final QuestionnaireRepo questionnaireRepo;

    public QuestionnaireDeployer(QuestionnaireRepo questionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
    }

    @Override
    public Questionnaire save(Questionnaire questionnaire) {
        return questionnaireRepo.save(questionnaire);
    }

    @Override
    public boolean delete(Questionnaire o) {
        return false;
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
