package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.dto.ConveyorProjectDto;
import com.kmzko.configurator.dto.PersonalConveyorDto;
import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.entity.user.conveyor.PersonalConveyor;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import com.kmzko.configurator.entity.user.questionnaire.PersonalRate;
import com.kmzko.configurator.mappers.ConveyorProjectMapper;
import com.kmzko.configurator.mappers.PersonalConveyorMapper;
import com.kmzko.configurator.mappers.PersonalQuestionnaireToQuestionnaireDtoMapper;
import com.kmzko.configurator.repositories.ConveyorProjectRepo;
import com.kmzko.configurator.repositories.UserRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConveyorProjectDetailService implements DetailService<ConveyorProjectDto> {
    private final ConveyorProjectRepo projectRepo;
    private final PersonalQuestionnaireDetailService questionnaireDetailService;
    private final ConveyorProjectMapper mapper;
    private final UserService userService;
    private final PersonalQuestionnaireToQuestionnaireDtoMapper questionnaireMapper;
    private final PersonalConveyorMapper conveyorMapper;
    private final UserRepo userRepo;

    public ConveyorProjectDetailService(ConveyorProjectRepo projectRepo,
                                        PersonalQuestionnaireDetailService questionnaireDetailService,
                                        ConveyorProjectMapper mapper, UserService userService,
                                        PersonalQuestionnaireToQuestionnaireDtoMapper questionnaireMapper,
                                        PersonalConveyorMapper conveyorMapper, UserRepo userRepo) {
        this.projectRepo = projectRepo;
        this.questionnaireDetailService = questionnaireDetailService;
        this.mapper = mapper;
        this.userService = userService;
        this.questionnaireMapper = questionnaireMapper;
        this.conveyorMapper = conveyorMapper;
        this.userRepo = userRepo;
    }

    public Optional<ConveyorProjectDto> updateById(ConveyorProjectDto conveyorProject, long id, String username) {
        Optional<ConveyorProject> project = userService.getConveyorProjectById(username, id);

        if (project.isPresent()) {
            ConveyorProject newProject = mapper.toEntity(conveyorProject);
            newProject.setUser(project.get().getUser());
            return Optional.of(mapper.toDto(projectRepo.save(newProject)));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<ConveyorProjectDto> updateQuestionnaireInProjectById(QuestionnaireDto questionnaireDto, long id, String username) {
        Optional<ConveyorProject> project = userService.getConveyorProjectById(username, id);

        if (project.isPresent()) {
            //Mutation
            updateQuestionnaire(project.get().getQuestionnaire(), questionnaireMapper.toEntity(questionnaireDto));

            project.get().getQuestionnaire().setConveyorProject(project.get());
            return Optional.of(mapper.toDto(projectRepo.save(project.get())));
        }
        else {
            return Optional.empty();
        }
    }

    private void updateQuestionnaire(PersonalQuestionnaire questionnaire, PersonalQuestionnaire newQuestionnaire) {
        for (Rate rate : questionnaire.getQuestionnaire().getRateList()) {
            Optional<PersonalRate> tmp = questionnaire.getRateList().stream().filter(i -> i.getRate().equals(rate)).findFirst();
            Optional<PersonalRate> personalRate = newQuestionnaire.getRateList().stream().filter(i -> i.getId().equals(tmp.get().getId())).findFirst();

            personalRate.ifPresent(value -> tmp.get().setValue(value.getValue()));
        }
    }

    public Optional<ConveyorProjectDto> updateConveyorInProjectById(PersonalConveyorDto personalConveyorDto, long id, String username) {
        Optional<ConveyorProject> project = userService.getConveyorProjectById(username, id);

        if (project.isPresent()) {
            project.get().setConveyor(conveyorMapper.toEntity(personalConveyorDto));
            project.get().getConveyor().setConveyorProject(project.get());
            return Optional.of(mapper.toDto(projectRepo.save(project.get())));
        }
        else {
            return Optional.empty();
        }
    }

    public ConveyorProjectDto createAndSaveProjectByType(ConveyorType type, String username) {
        Optional<User> user = userRepo.findByUsername(username);
        ConveyorProject project = new ConveyorProject();
        PersonalQuestionnaire questionnaire = questionnaireDetailService.createTemplateQuestionnaire(type);
        PersonalConveyor conveyor = new PersonalConveyor();

        conveyor.setType(type);

        conveyor.setConveyorProject(project);

        questionnaire.setConveyorProject(project);

        project.setUser(user.get());
        project.setConveyor(conveyor);
        project.setQuestionnaire(questionnaire);

        return mapper.toDto(projectRepo.save(project));
    }

    @Override
    public List<ConveyorProjectDto> getAll() {
        return projectRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ConveyorProjectDto> getById(long id) {
        Optional<ConveyorProject> conveyorProject = projectRepo.findById(id);

        return conveyorProject.map(mapper::toDto);
    }

    @Override
    public ConveyorProjectDto save(ConveyorProjectDto conveyorProject) {
        return mapper.toDto(projectRepo.save(mapper.toEntity(conveyorProject)));
    }

    @Override
    public boolean delete(ConveyorProjectDto conveyorProject) {
        try {
            projectRepo.delete(mapper.toEntity(conveyorProject));
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        try {
            projectRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}
