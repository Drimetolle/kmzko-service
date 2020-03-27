package com.kmzko.configurator.controllers;

import com.kmzko.configurator.entity.PersonalConveyor;
import com.kmzko.configurator.entity.PersonalQuestionnaire;
import com.kmzko.configurator.services.deployers.PersonalConveyorDetailService;
import com.kmzko.configurator.services.deployers.PersonalQuestionnaireDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserStaffController {
    private final PersonalConveyorDetailService personalConveyorService;
    private final PersonalQuestionnaireDetailService personalQuestionnaireService;

    public UserStaffController(PersonalConveyorDetailService personalConveyorService, PersonalQuestionnaireDetailService personalQuestionnaireService) {
        this.personalConveyorService = personalConveyorService;
        this.personalQuestionnaireService = personalQuestionnaireService;
    }

    @GetMapping(value = "/conveyors", produces = "application/json")
    public ResponseEntity<List<PersonalConveyor>> getUserConveyors() {
        return ResponseEntity.ok(personalConveyorService.getAll());
    }

    @GetMapping(value = "/conveyors/{id}", produces = "application/json")
    public ResponseEntity<PersonalConveyor> getUserConveyor(@PathVariable long id) {
        return ResponseEntity.ok(personalConveyorService.getById(id));
    }

    @PostMapping(value = "/conveyors", produces = "application/json")
    public ResponseEntity<PersonalConveyor> saveUserConveyor(@Valid @RequestBody PersonalConveyor body) {
        PersonalConveyor newBody = personalConveyorService.save(body);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @PutMapping(value = "/conveyors/{id}", produces = "application/json")
    public ResponseEntity<PersonalConveyor> changeUserConveyor(@Valid @RequestBody PersonalConveyor body,
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
    public ResponseEntity<List<PersonalQuestionnaire>> getUserQuestionnaires() {
        return ResponseEntity.ok(personalQuestionnaireService.getAll());
    }

    @GetMapping(value = "/questionnaires/{id}", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> getUserQuestionnaire(@PathVariable long id) {
        return ResponseEntity.ok(personalQuestionnaireService.getById(id));
    }

    @PostMapping(value = "/questionnaires", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> saveUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaire body) {
        PersonalQuestionnaire newBody = personalQuestionnaireService.save(body);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @PutMapping(value = "/questionnaires/{id}", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> changeUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaire body,
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
