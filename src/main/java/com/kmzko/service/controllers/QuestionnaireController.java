package com.kmzko.service.controllers;

import com.kmzko.service.domains.ConveyorType;
import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.services.GenerateQuestionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {
    @Autowired
    private GenerateQuestionnaire factoryQuestionnaire;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<String>> getListOfConveyorType() {
        List<ConveyorType> types = Arrays.asList(ConveyorType.values());
        List<String> result = types.stream().map(ConveyorType::getView).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{rawType}", produces = "application/json")
    public ResponseEntity<Questionnaire> getQuestionnaireByTypeConveyor(@PathVariable String rawType) {
        ConveyorType type = ConveyorType.saveValueOf(rawType);

        if (type == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Questionnaire questionnaire = factoryQuestionnaire.sad(type);

        return ResponseEntity.ok(questionnaire);
    }
}