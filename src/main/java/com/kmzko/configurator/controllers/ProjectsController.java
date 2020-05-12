package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.ConveyorProjectDto;
import com.kmzko.configurator.dto.PersonalConveyorDto;
import com.kmzko.configurator.dto.PersonalQuestionnaireDto;
import com.kmzko.configurator.dto.readonly.ConveyorProjectPreviewDto;
import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.mappers.*;
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

import static com.kmzko.configurator.utils.UserConverter.convertAuthenticationToUser;

@RestController
@RequestMapping("/api/user")
public class ProjectsController {
    private final PersonalConveyorMapper conveyorMapper;
    private final PersonalQuestionnaireMapper questionnaireMapper;
    private final ConveyorProjectMapper conveyorProjectMapper;
    private final ConveyorProjectViewMapper conveyorProjectViewMapper;
    private final UserService userService;
    private final ConveyorProjectDetailService projectDetailService;

    public ProjectsController(PersonalConveyorMapper conveyorMapper, PersonalQuestionnaireMapper questionnaireMapper,
                              ConveyorProjectMapper conveyorProjectMapper,
                              ConveyorProjectViewMapper conveyorProjectViewMapper,
                              UserService userService, ConveyorProjectDetailService projectDetailService) {
        this.conveyorMapper = conveyorMapper;
        this.questionnaireMapper = questionnaireMapper;
        this.conveyorProjectMapper = conveyorProjectMapper;
        this.conveyorProjectViewMapper = conveyorProjectViewMapper;
        this.userService = userService;
        this.projectDetailService = projectDetailService;
    }

    @GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConveyorProjectPreviewDto>> getUserConveyors(Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        return ResponseEntity.ok(userService.getAllConveyorProjects(user.getUsername()).stream()
                .map(conveyorProjectViewMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping(value = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorProjectDto> getUserConveyors(@PathVariable Long id, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        return ResponseEntity.ok(conveyorProjectMapper.toDto(userService.getConveyorProjectById(user.getUsername(), id).get()));
    }

    @PostMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorProjectDto> getUserConveyors(@RequestParam(name = "type", defaultValue = "tape") Optional<String> rawType,
                                                               Authentication authentication) {
        ConveyorType type = ConveyorType.safeValueOf(rawType.get());
        User user = convertAuthenticationToUser(authentication);

        ConveyorProject project = projectDetailService.createAndSaveProjectByType(type, user);

        ConveyorProjectDto newBody = conveyorProjectMapper.toDto(project);

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
        User user = convertAuthenticationToUser(authentication);

        Optional<ConveyorProject> project = projectDetailService.getById(body.getId());

        if (project.isPresent()) {
            ConveyorProject newProject = conveyorProjectMapper.toEntity(body);
            newProject.setId(project.get().getId());
            newProject.setUser(user);
            return ResponseEntity.ok(conveyorProjectMapper.toDto(projectDetailService.save(newProject)));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/projects/{id}/questionnaire", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalQuestionnaireDto> putQuestionnaireInProject(@Valid @RequestBody PersonalQuestionnaireDto body,
                                                                              @PathVariable long id, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);

        Optional<ConveyorProject> project = userService.getConveyorProjectById(user.getUsername(), id);

        if (project.isPresent()) {
            project.get().setQuestionnaire(questionnaireMapper.toEntity(body));
            project.get().getQuestionnaire().setConveyorProject(project.get());

            ConveyorProject newProject = projectDetailService.save(project.get());
            return ResponseEntity.ok(questionnaireMapper.toDto(newProject.getQuestionnaire()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(value = "/projects/{id}/conveyor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalConveyorDto> putConveyorInProject(@Valid @RequestBody PersonalConveyorDto body,
                                                                    @PathVariable long id, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);

        Optional<ConveyorProject> project = userService.getConveyorProjectById(user.getUsername(), id);

        if (project.isPresent()) {
            project.get().setConveyor(conveyorMapper.toEntity(body));
            ConveyorProject newProject = projectDetailService.save(project.get());
            return ResponseEntity.ok(conveyorMapper.toDto(newProject.getConveyor()));
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Void> deleteUserQuestionnaire(@PathVariable long id) {
        if (!projectDetailService.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
