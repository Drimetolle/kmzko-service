package com.kmzko.service.services;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.repositories.QuestionnaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateQuestionnaire {
    @Autowired
    private QuestionnaireRepo repository;

    public Questionnaire sad(ConveyorType type) {
        return new Questionnaire();
    }
}
