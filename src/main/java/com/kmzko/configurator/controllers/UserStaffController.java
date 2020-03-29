package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.PersonalConveyorDto;
import com.kmzko.configurator.dto.PersonalQuestionnaireDto;
import com.kmzko.configurator.mappers.PersonalConveyorMapper;
import com.kmzko.configurator.mappers.PersonalQuestionnaireMapper;
import com.kmzko.configurator.services.deployers.PersonalConveyorDetailService;
import com.kmzko.configurator.services.deployers.PersonalQuestionnaireDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserStaffController {
    private final PersonalConveyorDetailService personalConveyorService;
    private final PersonalQuestionnaireDetailService personalQuestionnaireService;
    private final PersonalConveyorMapper conveyorMapper;
    private final PersonalQuestionnaireMapper questionnaireMapper;

    public UserStaffController(PersonalConveyorDetailService personalConveyorService,
                               PersonalQuestionnaireDetailService personalQuestionnaireService,
                               PersonalConveyorMapper conveyorMapper, PersonalQuestionnaireMapper questionnaireMapper) {
        this.personalConveyorService = personalConveyorService;
        this.personalQuestionnaireService = personalQuestionnaireService;
        this.conveyorMapper = conveyorMapper;
        this.questionnaireMapper = questionnaireMapper;
    }

    @GetMapping(value = "/conveyors", produces = "application/json")
    public ResponseEntity<List<PersonalConveyorDto>> getUserConveyors() {
        return ResponseEntity.ok(personalConveyorService.getAll().stream().map(conveyorMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/conveyors/{id}", produces = "application/json")
    public ResponseEntity<PersonalConveyorDto> getUserConveyor(@PathVariable long id) {
        return ResponseEntity.ok(conveyorMapper.toDto(personalConveyorService.getById(id)));
    }

    @PostMapping(value = "/conveyors", produces = "application/json")
    public ResponseEntity<PersonalConveyorDto> saveUserConveyor(@Valid @RequestBody PersonalConveyorDto body) {
        PersonalConveyorDto newBody = conveyorMapper.toDto(personalConveyorService.save(conveyorMapper.toEntity(body)));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @PutMapping(value = "/conveyors/{id}", produces = "application/json")
    public ResponseEntity<PersonalConveyorDto> changeUserConveyor(@Valid @RequestBody PersonalConveyorDto body,
                                                               @PathVariable long id) {
        return null;
    }

    @DeleteMapping(value = "/conveyors/{id}")
    public ResponseEntity<Void> deleteUserConveyor(@PathVariable long id) {
        if (!personalConveyorService.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/questionnaires", produces = "application/json")
    public ResponseEntity<List<PersonalQuestionnaireDto>> getUserQuestionnaires() {
        return ResponseEntity.ok(personalQuestionnaireService.getAll().stream().map(questionnaireMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/questionnaires/{id}", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaireDto> getUserQuestionnaire(@PathVariable long id) {
        return ResponseEntity.ok(questionnaireMapper.toDto(personalQuestionnaireService.getById(id)));
    }

    @PostMapping(value = "/questionnaires", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaireDto> saveUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaireDto body) {
        PersonalQuestionnaireDto newBody = questionnaireMapper.toDto(personalQuestionnaireService.save(questionnaireMapper.toEntity(body)));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @PutMapping(value = "/questionnaires/{id}", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaireDto> changeUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaireDto body,
                                                                         @PathVariable long id) {
        return null;
    }

    @DeleteMapping(value = "/questionnaires/{id}")
    public ResponseEntity<Void> deleteUserQuestionnaire(@PathVariable long id) {
        if (!personalQuestionnaireService.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
