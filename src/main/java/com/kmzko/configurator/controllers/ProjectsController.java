package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.ConveyorProjectDto;
import com.kmzko.configurator.dto.PersonalConveyorDto;
import com.kmzko.configurator.dto.questionnaire.PersonalQuestionnaireDto;
import com.kmzko.configurator.dto.readonly.ConveyorProjectPreviewDto;
import com.kmzko.configurator.mappers.ConveyorProjectMapper;
import com.kmzko.configurator.mappers.ConveyorProjectViewMapper;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
public class ProjectsController {
    private final ConveyorProjectMapper conveyorProjectMapper;
    private final ConveyorProjectViewMapper conveyorProjectViewMapper;
    private final UserService userService;
    private final ConveyorProjectDetailService projectDetailService;

    public ProjectsController(ConveyorProjectMapper conveyorProjectMapper,
                              ConveyorProjectViewMapper conveyorProjectViewMapper,
                              UserService userService, ConveyorProjectDetailService projectDetailService) {
        this.conveyorProjectMapper = conveyorProjectMapper;
        this.conveyorProjectViewMapper = conveyorProjectViewMapper;
        this.userService = userService;
        this.projectDetailService = projectDetailService;
    }

    @GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConveyorProjectPreviewDto>> getUserConveyors(Authentication authentication) {
        return ResponseEntity.ok(userService.getAllConveyorProjects(authentication.getName()).stream()
                .map(conveyorProjectViewMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping(value = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorProjectDto> getUserConveyors(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.ok(conveyorProjectMapper.toDto(userService.getConveyorProjectById(authentication.getName(), id).get()));
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
    public ResponseEntity<ConveyorProjectDto> changeUserQuestionnaire(@Valid @RequestBody ConveyorProjectDto body,
                                                                      @PathVariable long id, Authentication authentication) {
        Optional<ConveyorProjectDto> project = projectDetailService.updateById(body, id, authentication.getName());

        if (project.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(project.get());
        }
    }

    @PutMapping(value = "/projects/{id}/questionnaire", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalQuestionnaireDto> putQuestionnaireInProject(@Valid @RequestBody PersonalQuestionnaireDto body,
                                                                              @PathVariable long id, Authentication authentication) {
        Optional<ConveyorProjectDto> project = projectDetailService.updateQuestionnaireInProjectById(body, id, authentication.getName());

        if (project.isPresent()) {
            return ResponseEntity.ok(project.get().getQuestionnaire());
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/projects/{id}/conveyor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> putConveyorInProject(@Valid @RequestBody PersonalConveyorDto body,
                                                                    @PathVariable long id, Authentication authentication) {
        Optional<ConveyorProjectDto> project = projectDetailService.updateConveyorInProjectById(body, id, authentication.getName());

        if (project.isPresent()) {
            return ResponseEntity.ok(project.get().getConveyor());
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Void> deleteUserQuestionnaire(@PathVariable long id, Authentication authentication) {
        if (!projectDetailService.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}