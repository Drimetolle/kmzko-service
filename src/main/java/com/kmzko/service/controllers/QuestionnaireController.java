package com.kmzko.service.controllers;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.services.GenerateQuestionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class QuestionnaireController {
    @Autowired
    private GenerateQuestionnaire factoryQuestionnaire;

    @GetMapping(value = "con/{rawType}", produces = "application/json")
    public String getQuestionnaireByTypeConveyor(@PathVariable String rawType) {
        ConveyorType type = ConveyorType.valueOf(rawType.toUpperCase());

        Questionnaire questionnaire = factoryQuestionnaire.sad(type);

        return type.toString();
    }
}
