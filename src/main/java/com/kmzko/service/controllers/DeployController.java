package com.kmzko.service.controllers;

import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.repositories.QuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/deploy")
@CrossOrigin(origins = "*")
public class DeployController {
    private final QuestionnaireRepo questionnaireRepo;

    public DeployController(QuestionnaireRepo questionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
    }

    @PostMapping(value = "/questionnaire")
    public ResponseEntity<Questionnaire> deployNewQuestionnaire(@Valid @RequestBody Questionnaire body) {
        Questionnaire newBody = questionnaireRepo.save(body);
        return ResponseEntity.created(URI.create("")).body(newBody);
    }

    @DeleteMapping(value = "/questionnaire/{id}")
    public ResponseEntity<Void> deleteQuestionnaire(@PathVariable long id) {
        try {
            questionnaireRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
