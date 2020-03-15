package com.kmzko.service.utils;

import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.repositories.QuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireDeployer {
    private final QuestionnaireRepo questionnaireRepo;

    public QuestionnaireDeployer(QuestionnaireRepo questionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
    }

    public Questionnaire save(Questionnaire questionnaire) {
        return questionnaireRepo.save(questionnaire);
    }

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
