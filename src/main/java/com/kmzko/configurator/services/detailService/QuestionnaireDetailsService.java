package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import com.kmzko.configurator.mappers.QuestionnaireMapper;
import com.kmzko.configurator.repositories.QuestionnaireRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionnaireDetailsService implements DetailService<QuestionnaireDto> {
    private final QuestionnaireRepo questionnaireRepo;
    private final QuestionnaireMapper mapper;

    public QuestionnaireDetailsService(QuestionnaireRepo questionnaireRepo, QuestionnaireMapper mapper) {
        this.questionnaireRepo = questionnaireRepo;
        this.mapper = mapper;
    }

    public QuestionnaireDto update(long id, QuestionnaireDto questionnaireDto) {
        Questionnaire questionnaire = questionnaireRepo.findById(id).get();
        Questionnaire newQuestionnaire = mapper.toEntity(questionnaireDto);

        questionnaire.setName(newQuestionnaire.getName());
        questionnaire.getRateList().clear();
        questionnaire.getRateList().addAll(newQuestionnaire.getRateList());

        return mapper.toDto(questionnaireRepo.save(newQuestionnaire));
    }

    public Optional<QuestionnaireDto> getLastRevisionQuestionnaire(ConveyorType type) {
        Optional<Questionnaire> questionnaire = questionnaireRepo.findLatestRecord(type.getValue());

        if (questionnaire.isPresent()) {
            return Optional.of(mapper.toDto(questionnaire.get()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public List<QuestionnaireDto> getAll() {
        return questionnaireRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionnaireDto> getById(long id) {
        Optional<Questionnaire> questionnaire = questionnaireRepo.findById(id);

        if (questionnaire.isPresent()) {
            return Optional.of(mapper.toDto(questionnaire.get()));
        }
        else return Optional.empty();
    }

    @Override
    public QuestionnaireDto save(QuestionnaireDto questionnaire) {
        return mapper.toDto(questionnaireRepo.save(mapper.toEntity(questionnaire)));
    }

    @Override
    public boolean delete(QuestionnaireDto questionnaire) {
        try {
            questionnaireRepo.delete(mapper.toEntity(questionnaire));
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
