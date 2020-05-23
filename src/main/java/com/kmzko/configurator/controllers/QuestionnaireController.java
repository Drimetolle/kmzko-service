package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import com.kmzko.configurator.services.detailService.QuestionnaireDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnaireController {
    private final QuestionnaireDetailsService detailService;

    public QuestionnaireController(QuestionnaireDetailsService detailService) {
        this.detailService = detailService;
    }

    @GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    // TODO добавить проверку есть ли для этого типа опросный лист
    public ResponseEntity<List<String>> getListOfConveyorType() {
        List<ConveyorType> types = Arrays.asList(ConveyorType.values());
        List<String> result = types.stream().map(ConveyorType::getView).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestionnaireDto>> getQuestionnaireByTypeConveyor(@RequestParam(name = "type") Optional<String> rawType) {
        ConveyorType type;
        if (rawType.isPresent()) {
            type = ConveyorType.safeValueOf(rawType.get());
            if (type == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Optional<QuestionnaireDto> questionnaire = detailService.getLastRevisionQuestionnaire(type);

            if (questionnaire.isPresent()) {
                return ResponseEntity.ok(new ArrayList<>());
            }

            return ResponseEntity.ok(Collections.singletonList(questionnaire.get()));
        }
        else {
            return ResponseEntity.ok(detailService.getAll());
        }
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionnaireDto> deployNewQuestionnaire(@Valid @RequestBody QuestionnaireDto body) {
        QuestionnaireDto newBody = detailService.save(body);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionnaireDto> deployNewQuestionnaire(@PathVariable long id, @Valid @RequestBody QuestionnaireDto body) {
        QuestionnaireDto newBody = detailService.update(id, body);

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
