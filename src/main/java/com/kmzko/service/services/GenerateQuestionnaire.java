package com.kmzko.service.services;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.repositories.QuestionnaireRepo;
import com.kmzko.service.repositories.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerateQuestionnaire {
    @Autowired
    private QuestionnaireRepo repository;

    @Autowired
    RateRepo rateRepo;

    public Questionnaire sad(ConveyorType type) {
        List<Questionnaire> questionnaireList = repository.findByType(type.toString());
        return questionnaireList.get(questionnaireList.size() - 1);
    }
}
