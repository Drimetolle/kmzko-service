package com.kmzko.service.controllers;

import com.kmzko.service.domains.Questionnaire;
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

    public DeployController(QuestionnaireDeployer questionnaireDeployer) {
        this.questionnaireDeployer = questionnaireDeployer;
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
}
