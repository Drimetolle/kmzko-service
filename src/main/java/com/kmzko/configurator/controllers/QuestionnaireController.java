package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.Questionnaire;
import com.kmzko.configurator.services.deployers.QuestionnaireDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questionnaires")
@CrossOrigin(origins = "*")
public class QuestionnaireController {
    private final QuestionnaireDetailService detailService;

    public QuestionnaireController(QuestionnaireDetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<String>> getListOfConveyorType() {
        List<ConveyorType> types = Arrays.asList(ConveyorType.values());
        List<String> result = types.stream().map(ConveyorType::getView).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{rawType}", produces = "application/json")
    public ResponseEntity<Questionnaire> getQuestionnaireByTypeConveyor(@PathVariable String rawType) {
        ConveyorType type = ConveyorType.safeValueOf(rawType);

        if (type == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Questionnaire questionnaire = detailService.getLastRevisionQuestionnaire(type);

        return ResponseEntity.ok(questionnaire);
    }

    @PostMapping
    public ResponseEntity<Questionnaire> deployNewQuestionnaire(@Valid @RequestBody Questionnaire body) {
        Questionnaire newBody = detailService.save(body);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(newBody);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteQuestionnaire(@PathVariable long id) {
        if (!detailService.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
