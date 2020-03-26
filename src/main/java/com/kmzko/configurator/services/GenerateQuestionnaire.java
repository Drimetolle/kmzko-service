package com.kmzko.configurator.services;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.Questionnaire;
import com.kmzko.configurator.repositories.QuestionnaireRepo;
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
