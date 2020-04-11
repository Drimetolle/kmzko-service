package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.PersonalConveyorDto;
import com.kmzko.configurator.dto.PersonalQuestionnaireDto;
import com.kmzko.configurator.dto.UserDto;
import com.kmzko.configurator.entity.user.PersonalConveyor;
import com.kmzko.configurator.entity.user.PersonalQuestionnaire;
import com.kmzko.configurator.mappers.PersonalConveyorMapper;
import com.kmzko.configurator.mappers.PersonalQuestionnaireMapper;
import com.kmzko.configurator.services.detailService.PersonalConveyorDetailService;
import com.kmzko.configurator.services.detailService.PersonalQuestionnaireDetailService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.*;
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

    @GetMapping(value = "/conveyors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonalConveyorDto>> getUserConveyors() {
        return ResponseEntity.ok(personalConveyorService.getAll().stream().map(conveyorMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/conveyors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> getUserConveyor(@PathVariable long id) {
        Optional<PersonalConveyor> conveyor = personalConveyorService.getById(id);

        if (conveyor.isPresent()) {
            return ResponseEntity.ok(conveyorMapper.toDto(conveyor.get()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/conveyors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> saveUserConveyor(@Valid @RequestBody PersonalConveyorDto body) {
        // TODO
        UserDto user = new UserDto("123", "123", "123", "name", new HashSet<>());
        user.setId(Long.valueOf(1));
        body.setUser(user);
        return createConveyor(body);
    }

    @PutMapping(value = "/conveyors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> changeUserConveyor(@Valid @RequestBody PersonalConveyorDto body,
                                                               @PathVariable long id) {
        Optional<PersonalConveyor> conveyor = personalConveyorService.getById(id);

        if (conveyor.isPresent()) {
            PersonalConveyor newConveyor = conveyorMapper.toEntity(body);
            newConveyor.setId(conveyor.get().getId());
            return ResponseEntity.ok(conveyorMapper.toDto(personalConveyorService.save(newConveyor)));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping(value = "/conveyors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> patchUserConveyor(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
        Optional<PersonalConveyor> conveyor = personalConveyorService.getById(id);

        if (conveyor.isPresent()) {
            PersonalConveyor newConveyor = conveyor.get();
            fields.forEach((k, v) -> {
                // use reflection to get field k on manager and set it to value k
                Field field = ReflectionUtils.findField(PersonalConveyorDto.class, (String) k);
                ReflectionUtils.setField(field, newConveyor, v);
            });

            newConveyor.setId(newConveyor.getId());
            return ResponseEntity.ok(conveyorMapper.toDto(personalConveyorService.save(newConveyor)));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "/conveyors/{id}")
    public ResponseEntity<Void> deleteUserConveyor(@PathVariable long id) {
        if (!personalConveyorService.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<PersonalConveyorDto> createConveyor(PersonalConveyorDto conveyorDto) {
        PersonalConveyorDto newBody = conveyorMapper.toDto(personalConveyorService.save(conveyorMapper.toEntity(conveyorDto)));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @GetMapping(value = "/questionnaires", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonalQuestionnaireDto>> getUserQuestionnaires() {
        return ResponseEntity.ok(personalQuestionnaireService.getAll().stream().map(questionnaireMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/questionnaires/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalQuestionnaireDto> getUserQuestionnaire(@PathVariable long id) {
        Optional<PersonalQuestionnaire> questionnaire = personalQuestionnaireService.getById(id);

        if (questionnaire.isPresent()) {
            return ResponseEntity.ok(questionnaireMapper.toDto(questionnaire.get()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/questionnaires", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PutMapping(value = "/questionnaires/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
