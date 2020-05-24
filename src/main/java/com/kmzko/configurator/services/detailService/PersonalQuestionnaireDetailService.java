package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import com.kmzko.configurator.entity.user.questionnaire.PersonalRate;
import com.kmzko.configurator.repositories.PersonalQuestionnaireRepo;
import com.kmzko.configurator.repositories.QuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonalQuestionnaireDetailService implements DetailService<PersonalQuestionnaire> {
    private final PersonalQuestionnaireRepo questionnaireRepo;
    private final QuestionnaireRepo templateQuestionnaireRepo;

    public PersonalQuestionnaireDetailService(PersonalQuestionnaireRepo questionnaireRepo, QuestionnaireRepo templateQuestionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
        this.templateQuestionnaireRepo = templateQuestionnaireRepo;
    }

    public PersonalQuestionnaire createTemplateQuestionnaire(ConveyorType type) {
        Questionnaire questionnaire = templateQuestionnaireRepo.findLatestRecord(type.name()).get();

        return createQuestionnaireTemplate(questionnaire);
    }

    private PersonalQuestionnaire createQuestionnaireTemplate(Questionnaire questionnaire) {
        PersonalQuestionnaire personalQuestionnaire = new PersonalQuestionnaire();

        personalQuestionnaire.setQuestionnaire(questionnaire);
        personalQuestionnaire.setRateList(questionnaire.getRateList().stream().map(this::createRate).collect(Collectors.toList()));

        return personalQuestionnaire;
    }

    private PersonalRate createRate(Rate rate) {
        PersonalRate personalRate = new PersonalRate();

        personalRate.setRate(rate);
        personalRate.setValue(rate.getValue());

        return personalRate;
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
