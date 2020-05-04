package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.BioDto;
import com.kmzko.configurator.dto.ConveyorProjectDto;
import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.mappers.ConveyorProjectMapper;
import com.kmzko.configurator.mappers.PersonalConveyorMapper;
import com.kmzko.configurator.mappers.PersonalQuestionnaireMapper;
import com.kmzko.configurator.mappers.UserBioMapper;
import com.kmzko.configurator.security.jwt.JwtUser;
import com.kmzko.configurator.services.detailService.ConveyorProjectDetailService;
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
    private final ConveyorProjectMapper conveyorProjectMapper;
    private final UserBioMapper userBioMapper;
    private final UserService userService;
    private final PersonalConveyorDetailService personalConveyorService;
    private final PersonalQuestionnaireDetailService personalQuestionnaireService;
    private final ConveyorProjectDetailService projectDetailService;

    public UserStaffController(PersonalConveyorMapper conveyorMapper,
                               PersonalQuestionnaireMapper questionnaireMapper,
                               ConveyorProjectMapper conveyorProjectMapper,
                               UserBioMapper userBioMapper,
                               UserService userService,
                               PersonalConveyorDetailService personalConveyorService,
                               PersonalQuestionnaireDetailService personalQuestionnaireService,
                               ConveyorProjectDetailService projectDetailService) {
        this.conveyorMapper = conveyorMapper;
        this.questionnaireMapper = questionnaireMapper;
        this.conveyorProjectMapper = conveyorProjectMapper;
        this.userBioMapper = userBioMapper;
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

        user = userService.findByUsername(user.getUsername());

        user.setName(body.getName());
        user.setEmail(body.getEmail());

        return ResponseEntity.ok(userBioMapper.toDto(user));
    }

    @GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConveyorProjectDto>> getUserConveyors(@RequestParam String latest, Authentication authentication) {
        User user = convertAuthenticationToUser(authentication);
        return ResponseEntity.ok(userService.getAllConveyorProjects(user.getUsername()).stream()
                .map(conveyorProjectMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConveyorProjectDto> getUserConveyors(Authentication authentication, ConveyorProjectDto body) {
        User user = convertAuthenticationToUser(authentication);
        ConveyorProject project = conveyorProjectMapper.toEntity(body);
        project.setUser(user);

        ConveyorProjectDto newBody = conveyorProjectMapper.toDto(projectDetailService.save(project));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBody.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(newBody);
    }

    @PutMapping(value = "/questionnaires/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @DeleteMapping(value = "/questionnaires/{id}")
    public ResponseEntity<Void> deleteUserQuestionnaire(@PathVariable long id) {
        if (!projectDetailService.deleteById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    private User convertAuthenticationToUser(Authentication authentication) {
        JwtUser jwtUser =(JwtUser) authentication.getPrincipal();
        return userService.findByUsername(jwtUser.getUsername());
    }
}
