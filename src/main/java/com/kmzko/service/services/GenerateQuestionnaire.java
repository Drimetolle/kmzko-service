package com.kmzko.service.services;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.repositories.QuestionnaireRepo;
import org.springframework.stereotype.Service;

@Service
public class GenerateQuestionnaire {
    private final QuestionnaireRepo repository;

    public GenerateQuestionnaire(QuestionnaireRepo repository) {
        this.repository = repository;
    }

    public Questionnaire getLastRevisionQuestionnaire(ConveyorType type) {
        return repository.findLatestRecord(type.getValue());
    }
}
