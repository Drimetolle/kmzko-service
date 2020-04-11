package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.dto.QuestionnaireDto;
import com.kmzko.configurator.mappers.QuestionnaireMapper;
import com.kmzko.configurator.services.detailService.QuestionnaireDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final QuestionnaireMapper mapper;

    public QuestionnaireController(QuestionnaireDetailService detailService, QuestionnaireMapper mapper) {
        this.detailService = detailService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getListOfConveyorType() {
        List<ConveyorType> types = Arrays.asList(ConveyorType.values());
        List<String> result = types.stream().map(ConveyorType::getView).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{rawType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionnaireDto> getQuestionnaireByTypeConveyor(@PathVariable String rawType) {
        ConveyorType type = ConveyorType.safeValueOf(rawType);

        if (type == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        QuestionnaireDto questionnaire = mapper.toDto(detailService.getLastRevisionQuestionnaire(type));

        return ResponseEntity.ok(questionnaire);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionnaireDto> deployNewQuestionnaire(@Valid @RequestBody QuestionnaireDto body) {
        QuestionnaireDto newBody = mapper.toDto(detailService.save(mapper.toEntity(body)));
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
