package com.kmzko.service.controllers;

import com.kmzko.service.entity.PersonalConveyor;
import com.kmzko.service.entity.PersonalQuestionnaire;
import com.kmzko.service.services.deployers.PersonalConveyorDeployer;
import com.kmzko.service.services.deployers.PersonalQuestionnaireDeployer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserStaffController {
    private final PersonalConveyorDeployer personalConveyorDeployer;
    private final PersonalQuestionnaireDeployer personalQuestionnaireDeployer;

    public UserStaffController(PersonalConveyorDeployer personalConveyorDeployer, PersonalQuestionnaireDeployer personalQuestionnaireDeployer) {
        this.personalConveyorDeployer = personalConveyorDeployer;
        this.personalQuestionnaireDeployer = personalQuestionnaireDeployer;
    }

    @GetMapping(value = "/conveyor", produces = "application/json")
    public ResponseEntity<PersonalConveyor> getUserConveyor(@Valid @RequestBody PersonalConveyor body) {
        return null;
    }

    @PostMapping(value = "/conveyor", produces = "application/json")
    public ResponseEntity<PersonalConveyor> saveUserConveyor(HttpServletRequest request,
                                                             @Valid @RequestBody PersonalConveyor body) {
        return null;
    }

    @PutMapping(value = "/conveyor/{id}", produces = "application/json")
    public ResponseEntity<PersonalConveyor> changeUserConveyor(@Valid @RequestBody PersonalConveyor body,
                                                               @PathVariable long id) {
        return null;
    }

    @DeleteMapping(value = "/conveyor/{id}")
    public ResponseEntity<Void> deleteUserConveyor(@PathVariable long id) {
        if (!personalConveyorDeployer.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/questionnaire", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> getUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaire body) {
        return null;
    }

    @PostMapping(value = "/questionnaire", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> saveUserQuestionnaire(HttpServletRequest request,
                                                                       @Valid @RequestBody PersonalQuestionnaire body) {
        return null;
    }

    @PutMapping(value = "/questionnaire/{id}", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> changeUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaire body,
                                                                         @PathVariable long id) {
        return null;
    }

    @DeleteMapping(value = "/questionnaire/{id}")
    public ResponseEntity<Void> deleteUserQuestionnaire(@PathVariable long id) {
        if (!personalQuestionnaireDeployer.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
