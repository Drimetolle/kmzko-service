package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.PersonalConveyorDto;
import com.kmzko.configurator.dto.PersonalQuestionnaireDto;
import com.kmzko.configurator.entity.user.PersonalConveyor;
import com.kmzko.configurator.entity.user.PersonalQuestionnaire;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.mappers.PersonalConveyorMapper;
import com.kmzko.configurator.mappers.PersonalQuestionnaireMapper;
import com.kmzko.configurator.security.jwt.JwtUser;
import com.kmzko.configurator.services.detailService.PersonalConveyorDetailService;
import com.kmzko.configurator.services.detailService.PersonalQuestionnaireDetailService;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserStaffController {
    private final PersonalConveyorMapper conveyorMapper;
    private final PersonalQuestionnaireMapper questionnaireMapper;
    private final UserService userService;
    private final PersonalConveyorDetailService personalConveyorService;
    private final PersonalQuestionnaireDetailService personalQuestionnaireService;

    public UserStaffController(PersonalConveyorMapper conveyorMapper,
                               PersonalQuestionnaireMapper questionnaireMapper, UserService userService,
                               PersonalConveyorDetailService personalConveyorService,
                               PersonalQuestionnaireDetailService personalQuestionnaireService) {
        this.conveyorMapper = conveyorMapper;
        this.questionnaireMapper = questionnaireMapper;
        this.userService = userService;
        this.personalConveyorService = personalConveyorService;
        this.personalQuestionnaireService = personalQuestionnaireService;
    }

    @GetMapping(value = "/conveyors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonalConveyorDto>> getUserConveyors(Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        return ResponseEntity.ok(userService.getAllUserConveyors(user.getUsername()).stream().map(conveyorMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/conveyors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> getUserConveyor(@PathVariable long id, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        Optional<PersonalConveyor> conveyor = userService.getUserConveyorsById(id, user.getUsername());

        if (conveyor.isPresent()) {
            return ResponseEntity.ok(conveyorMapper.toDto(conveyor.get()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/conveyors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> saveUserConveyor(@Valid @RequestBody PersonalConveyorDto body,
                                                                Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        PersonalConveyor conveyor = conveyorMapper.toEntity(body);
        conveyor.setUser(user);

        PersonalConveyorDto newBody = conveyorMapper.toDto(personalConveyorService.save(conveyor));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @PutMapping(value = "/conveyors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> changeUserConveyor(@Valid @RequestBody PersonalConveyorDto body,
                                                               @PathVariable long id, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        Optional<PersonalConveyor> conveyor = userService.getAllUserConveyors(id).stream()
                .filter(c -> c.getId() == id).findFirst();

        if (conveyor.isPresent()) {
            PersonalConveyor newConveyor = conveyorMapper.toEntity(body);
            newConveyor.setId(conveyor.get().getId());
            newConveyor.setUser(user);
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

    @GetMapping(value = "/questionnaires", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonalQuestionnaireDto>> getUserQuestionnaires(Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        return ResponseEntity.ok(userService.getAllUserQuestionnaires(user.getUsername()).stream()
                .map(questionnaireMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/questionnaires/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalQuestionnaireDto> getUserQuestionnaire(@PathVariable long id,
                                                                         Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        Optional<PersonalQuestionnaire> questionnaire = userService.getUserQuestionnairesById(id, user.getUsername());

        if (questionnaire.isPresent()) {
            return ResponseEntity.ok(questionnaireMapper.toDto(questionnaire.get()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/questionnaires", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalQuestionnaireDto> saveUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaireDto body,
                                                                          Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        PersonalQuestionnaire questionnaire = questionnaireMapper.toEntity(body);
        questionnaire.setUser(user);

        PersonalQuestionnaireDto newBody = questionnaireMapper.toDto(personalQuestionnaireService.save(questionnaire));

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
                                                                         @PathVariable long id, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);

        Optional<PersonalQuestionnaire> questionnaire = userService.getAllUserQuestionnaires(user.getId()).stream()
                .filter(q -> q.getId() == id).findFirst();

        if (questionnaire.isPresent()) {
            PersonalQuestionnaire newQuestionnaire = questionnaireMapper.toEntity(body);
            newQuestionnaire.setId(questionnaire.get().getId());
            newQuestionnaire.setUser(user);
            return ResponseEntity.ok(questionnaireMapper.toDto(personalQuestionnaireService.save(newQuestionnaire)));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "/questionnaires/{id}")
    public ResponseEntity<Void> deleteUserQuestionnaire(@PathVariable long id) {
        if (!personalQuestionnaireService.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    private User convertAuthenticationToUser(Authentication authentication) {
        JwtUser jwtUser =(JwtUser) authentication.getPrincipal();
        return userService.findByUsername(jwtUser.getUsername());
    }
}
