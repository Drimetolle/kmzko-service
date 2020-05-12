package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.entity.user.conveyor.PersonalConveyor;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import com.kmzko.configurator.entity.user.questionnaire.PersonalRate;
import com.kmzko.configurator.repositories.ConveyorProjectRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConveyorProjectDetailService implements DetailService<ConveyorProject> {
    private final ConveyorProjectRepo projectRepo;
    private final PersonalQuestionnaireDetailService questionnaireDetailService;
    private final PersonalConveyorDetailService conveyorDetailService;

    public ConveyorProjectDetailService(ConveyorProjectRepo projectRepo, PersonalQuestionnaireDetailService questionnaireDetailService, PersonalConveyorDetailService conveyorDetailService) {
        this.projectRepo = projectRepo;
        this.questionnaireDetailService = questionnaireDetailService;
        this.conveyorDetailService = conveyorDetailService;
    }

    public ConveyorProject createAndSaveProjectByType(ConveyorType type, User user) {
        ConveyorProject project = new ConveyorProject();
        PersonalQuestionnaire questionnaire = questionnaireDetailService.createTemplateQuestionnaire(type);
        PersonalConveyor conveyor = new PersonalConveyor();

        conveyor.setType(type);

        conveyor.setConveyorProject(project);

        questionnaire.setConveyorProject(project);
        removeIds(questionnaire);

        project.setUser(user);
        project.setConveyor(conveyor);
        project.setQuestionnaire(questionnaire);
        //TODO

        return save(project);
    }

    private void removeIds(PersonalQuestionnaire questionnaire) {
        questionnaire.setId(null);

        List<PersonalRate> newRates = questionnaire.getRateList().stream().map(r -> {
            r.setId(null);
            return r;
        }).collect(Collectors.toList());
        questionnaire.setRateList(newRates);
    }

    @Override
    public List<ConveyorProject> getAll() {
        return projectRepo.findAll();
    }

    @Override
    public Optional<ConveyorProject> getById(long id) {
        return projectRepo.findById(id);
    }

    @Override
    public ConveyorProject save(ConveyorProject conveyorProject) {
        return projectRepo.save(conveyorProject);
    }

    @Override
    public boolean delete(ConveyorProject conveyorProject) {
        try {
            projectRepo.delete(conveyorProject);
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
