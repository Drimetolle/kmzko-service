package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import com.kmzko.configurator.mappers.PersonalQuestionnaireAndQuestionnaire;
import com.kmzko.configurator.mappers.QuestionnaireMapper;
import com.kmzko.configurator.repositories.PersonalQuestionnaireRepo;
import com.kmzko.configurator.repositories.QuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalQuestionnaireDetailService implements DetailService<PersonalQuestionnaire> {
    private final PersonalQuestionnaireRepo questionnaireRepo;
    private final QuestionnaireRepo templateQuestionnaireRepo;
    private final QuestionnaireMapper questionnaireMapper;
    private final PersonalQuestionnaireAndQuestionnaire personalQuestionnaireAndQuestionnaire;

    public PersonalQuestionnaireDetailService(PersonalQuestionnaireRepo questionnaireRepo, QuestionnaireRepo templateQuestionnaireRepo, QuestionnaireMapper questionnaireMapper, PersonalQuestionnaireAndQuestionnaire personalQuestionnaireAndQuestionnaire) {
        this.questionnaireRepo = questionnaireRepo;
        this.templateQuestionnaireRepo = templateQuestionnaireRepo;
        this.questionnaireMapper = questionnaireMapper;
        this.personalQuestionnaireAndQuestionnaire = personalQuestionnaireAndQuestionnaire;
    }

    public PersonalQuestionnaire createTemplateQuestionnaire(ConveyorType type) {
        Questionnaire questionnaire = templateQuestionnaireRepo.findLatestRecord(type.name()).get();
        PersonalQuestionnaire personalQuestionnaire = personalQuestionnaireAndQuestionnaire.toEntity(questionnaireMapper.toDto(questionnaire));

        return personalQuestionnaire;
    }

    @Override
    public List<PersonalQuestionnaire> getAll() {
        return questionnaireRepo.findAll();
    }

    @Override
    public Optional<PersonalQuestionnaire> getById(long id) {
        return questionnaireRepo.findById(id);
    }

    @Override
    public PersonalQuestionnaire save(PersonalQuestionnaire questionnaire) {
        return questionnaireRepo.save(questionnaire);
    }

    @Override
    public PersonalQuestionnaire update(PersonalQuestionnaire personalQuestionnaire) {
        return null;
    }

    @Override
    public boolean delete(PersonalQuestionnaire questionnaire) {
        try {
            questionnaireRepo.delete(questionnaire);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        try {
            questionnaireRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}
