package com.kmzko.configurator.controllers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.*;
import com.kmzko.configurator.dto.ConveyorProjectDto;
import com.kmzko.configurator.dto.readonly.ConveyorProjectPreviewDto;
import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.mappers.*;
import com.kmzko.configurator.security.jwt.JwtUser;
import com.kmzko.configurator.services.detailService.ConveyorProjectDetailService;
import com.kmzko.configurator.services.detailService.PersonalConveyorDetailService;
import com.kmzko.configurator.services.detailService.PersonalQuestionnaireDetailService;
import com.kmzko.configurator.services.detailService.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final ConveyorProjectMapper conveyorProjectMapper;
    private final UserBioMapper userBioMapper;
    private final ConveyorProjectViewMapper conveyorProjectViewMapper;
    private final UserService userService;
    private final PersonalConveyorDetailService personalConveyorService;
    private final PersonalQuestionnaireDetailService personalQuestionnaireService;
    private final ConveyorProjectDetailService projectDetailService;

    public UserStaffController(PersonalConveyorMapper conveyorMapper,
                               PersonalQuestionnaireMapper questionnaireMapper,
                               ConveyorProjectMapper conveyorProjectMapper,
                               UserBioMapper userBioMapper,
                               ConveyorProjectViewMapper conveyorProjectViewMapper, UserService userService,
                               PersonalConveyorDetailService personalConveyorService,
                               PersonalQuestionnaireDetailService personalQuestionnaireService,
                               ConveyorProjectDetailService projectDetailService) {
        this.conveyorMapper = conveyorMapper;
        this.questionnaireMapper = questionnaireMapper;
        this.conveyorProjectMapper = conveyorProjectMapper;
        this.userBioMapper = userBioMapper;
        this.conveyorProjectViewMapper = conveyorProjectViewMapper;
        this.userService = userService;
        this.personalConveyorService = personalConveyorService;
        this.personalQuestionnaireService = personalQuestionnaireService;
        this.projectDetailService = projectDetailService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BioDto> getUser(Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        return ResponseEntity.ok(userBioMapper.toDto(user));
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BioDto> editBioUser(@Valid @RequestBody BioDto body, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);

        user = userService.findByUsername(user.getUsername()).get();

        user.setName(body.getName());
        user.setEmail(body.getEmail());

        return ResponseEntity.ok(userBioMapper.toDto(user));
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

    private User convertAuthenticationToUser(Authentication authentication) {
        JwtUser jwtUser =(JwtUser) authentication.getPrincipal();
        String name = jwtUser.getUsername();

        return userService.findByUsername(jwtUser.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username: " + name + "not found"));
    }
}
