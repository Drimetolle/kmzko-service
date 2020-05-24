package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import com.kmzko.configurator.entity.user.questionnaire.TestP;
import com.kmzko.configurator.entity.user.questionnaire.TestQ;
import com.kmzko.configurator.mappers.PersonalQuestionnaireToQuestionnaireDto;
import com.kmzko.configurator.repositories.PersonalQuestionnaire1Repo;
import com.kmzko.configurator.repositories.QuestionnaireRepo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class Test {
    private final PersonalQuestionnaire1Repo repo;
    private final QuestionnaireRepo questionnaireRepo;
    private final PersonalQuestionnaireToQuestionnaireDto questionnaireToQuestionnaireDto;

    public Test(PersonalQuestionnaire1Repo repo, QuestionnaireRepo questionnaireRepo, PersonalQuestionnaireToQuestionnaireDto questionnaireToQuestionnaireDto) {
        this.repo = repo;
        this.questionnaireRepo = questionnaireRepo;
        this.questionnaireToQuestionnaireDto = questionnaireToQuestionnaireDto;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionnaireDto> searchByConwefwefwefveyors(@RequestParam Map<String, String> allParams) {
        return ResponseEntity.ok(questionnaireToQuestionnaireDto.toDto(repo.getOne(102L)));
    }

    @GetMapping(value = "/123", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchByConveyors(@RequestParam Map<String, String> allParams) {
        Questionnaire questionnaire = questionnaireRepo.findById(3L).get();

        TestQ q = new TestQ();
        q.setQuestionnaire(questionnaire);


        List<TestP> testPS = new ArrayList<>();

        for (Rate rate : questionnaire.getRateList()) {
            TestP testP = new TestP();
            testP.setValue("sor");
            testP.setRate(rate);

            testPS.add(testP);
        }


        q.getRateList().addAll(testPS);

        TestQ testQ = repo.save(q);

        return ResponseEntity.ok(testQ);
    }
}
