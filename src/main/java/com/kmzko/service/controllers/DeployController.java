package com.kmzko.service.controllers;

import com.kmzko.service.domains.Questionnaire;
import com.kmzko.service.entity.PersonalConveyor;
import com.kmzko.service.entity.PersonalQuestionnaire;
import com.kmzko.service.services.deployers.PersonalConveyorDeployer;
import com.kmzko.service.services.deployers.PersonalQuestionnaireDeployer;
import com.kmzko.service.services.deployers.QuestionnaireDeployer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/deploy")
@CrossOrigin(origins = "*")
public class DeployController {
    private final QuestionnaireDeployer questionnaireDeployer;
    private final PersonalConveyorDeployer personalConveyorDeployer;
    private final PersonalQuestionnaireDeployer personalQuestionnaireDeployer;

    public DeployController(QuestionnaireDeployer questionnaireDeployer,
                            PersonalConveyorDeployer personalConveyorDeployer,
                            PersonalQuestionnaireDeployer personalQuestionnaireDeployer) {
        this.questionnaireDeployer = questionnaireDeployer;
        this.personalConveyorDeployer = personalConveyorDeployer;
        this.personalQuestionnaireDeployer = personalQuestionnaireDeployer;
    }

    @PostMapping(value = "/questionnaire")
    public ResponseEntity<Questionnaire> deployNewQuestionnaire(HttpServletRequest request,
                                                                @Valid @RequestBody Questionnaire body) {
        Questionnaire newBody = questionnaireDeployer.save(body);
        return ResponseEntity.created(URI.create(
                String.format("http://%s%s%s", request.getLocalName(), "/api/questionnaire/", newBody.getId())))
                .body(newBody);
    }

    @DeleteMapping(value = "/questionnaire/{id}")
    public ResponseEntity<Void> deleteQuestionnaire(@PathVariable long id) {
        if (!questionnaireDeployer.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user/conveyor", produces = "application/json")
    public ResponseEntity<PersonalConveyor> getUserConveyor(@Valid @RequestBody PersonalConveyor body) {
        return null;
    }

    @PostMapping(value = "/user/conveyor", produces = "application/json")
    public ResponseEntity<PersonalConveyor> saveUserConveyor(HttpServletRequest request,
                                                         @Valid @RequestBody PersonalConveyor body) {
        return null;
    }

    @PutMapping(value = "/user/conveyor/{id}", produces = "application/json")
    public ResponseEntity<PersonalConveyor> changeUserConveyor(@Valid @RequestBody PersonalConveyor body,
                                                               @PathVariable long id) {
        return null;
    }

    @DeleteMapping(value = "/user/conveyor/{id}")
    public ResponseEntity<Void> deleteUserConveyor(@PathVariable long id) {
        if (!personalConveyorDeployer.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user/questionnaire", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> getUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaire body) {
        return null;
    }

    @PostMapping(value = "/user/questionnaire", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> saveUserQuestionnaire(HttpServletRequest request,
                                                                  @Valid @RequestBody PersonalQuestionnaire body) {
        return null;
    }

    @PutMapping(value = "/user/questionnaire/{id}", produces = "application/json")
    public ResponseEntity<PersonalQuestionnaire> changeUserQuestionnaire(@Valid @RequestBody PersonalQuestionnaire body,
                                                               @PathVariable long id) {
        return null;
    }

    @DeleteMapping(value = "/user/questionnaire/{id}")
    public ResponseEntity<Void> deleteUserQuestionnaire(@PathVariable long id) {
        if (!personalQuestionnaireDeployer.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
