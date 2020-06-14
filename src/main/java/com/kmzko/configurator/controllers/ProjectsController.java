package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.ConveyorProjectDto;
import com.kmzko.configurator.dto.PersonalConveyorDto;
import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import com.kmzko.configurator.dto.readonly.ConveyorProjectPreviewDto;
import com.kmzko.configurator.services.detailService.ConveyorProjectDetailService;
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


@RestController
@RequestMapping("/api/user")
public class ProjectsController {
    private final UserService userService;
    private final ConveyorProjectDetailService projectDetailService;

    public ProjectsController(UserService userService, ConveyorProjectDetailService projectDetailService) {
        this.userService = userService;
        this.projectDetailService = projectDetailService;
    }

    @GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConveyorProjectPreviewDto>> getUserConveyors(Authentication authentication) {
        return ResponseEntity.ok(userService.getAllConveyorProjectsPreview(authentication.getName()));
    }

    @GetMapping(value = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorProjectDto> getUserConveyors(@PathVariable Long id, Authentication authentication) {
        Optional<ConveyorProjectDto> project = userService.getConveyorProjectById(authentication.getName(), id);
        if(project.isPresent()) {
            return ResponseEntity.ok(project.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorProjectDto> getUserConveyors(@RequestParam(name = "type", defaultValue = "tape") Optional<String> rawType,
                                                               Authentication authentication) {
        ConveyorType type = ConveyorType.safeValueOf(rawType.get());

        ConveyorProjectDto newBody = projectDetailService.createAndSaveProjectByType(type, authentication.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @PutMapping(value = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorProjectDto> updateUserProject(@Valid @RequestBody ConveyorProjectDto body,
                                                                      @PathVariable long id, Authentication authentication) {
        Optional<ConveyorProjectDto> project = projectDetailService.updateById(body, id, authentication.getName());

        if (project.isPresent()) {
            return ResponseEntity.ok(project.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/projects/{id}/questionnaire", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionnaireDto> updateQuestionnaireInProject(@Valid @RequestBody QuestionnaireDto body,
                                                                              @PathVariable long id, Authentication authentication) {
        Optional<ConveyorProjectDto> project = projectDetailService.updateQuestionnaireInProjectById(body, id, authentication.getName());

        if (project.isPresent()) {
            return ResponseEntity.ok(project.get().getQuestionnaire());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/projects/{id}/conveyor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> updateConveyorInProject(@Valid @RequestBody PersonalConveyorDto body,
                                                                    @PathVariable long id, Authentication authentication) {
        Optional<ConveyorProjectDto> project = projectDetailService.updateConveyorInProjectById(body, id, authentication.getName());

        if (project.isPresent()) {
            return ResponseEntity.ok(project.get().getConveyor());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Void> deleteUserProject(@PathVariable long id, Authentication authentication) {
        if (!projectDetailService.deleteById(authentication.getName(), id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
