package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.dto.questionnaire.PossibleRateDto;
import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import com.kmzko.configurator.dto.questionnaire.RateDto;
import com.kmzko.configurator.entity.user.questionnaire.PersonalRate;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class PersonalQuestionnaireToQuestionnaireDtoMapper extends AbstractMapper<PersonalQuestionnaire, QuestionnaireDto> {
    private final ModelMapper mapper;

    public PersonalQuestionnaireToQuestionnaireDtoMapper(ModelMapper mapper) {
        super(PersonalQuestionnaire.class, QuestionnaireDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(PersonalQuestionnaire.class, QuestionnaireDto.class)
                .addMappings(m -> m.skip(QuestionnaireDto::setName)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(QuestionnaireDto::setType)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(QuestionnaireDto::setRateList)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(QuestionnaireDto.class, PersonalQuestionnaire.class)
                .addMappings(m -> m.skip(PersonalQuestionnaire::setRateList)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(PersonalQuestionnaire source, QuestionnaireDto destination) {
        Questionnaire questionnaire = source.getQuestionnaire();
        destination.setType(questionnaire.getType().getView());
        destination.setName(questionnaire.getName());

        destination.setRateList(source.getRateList().stream().map(this::createRateDto).collect(Collectors.toList()));
    }

    @Override
    void mapSpecificFields(QuestionnaireDto source, PersonalQuestionnaire destination) {
        destination.setRateList(source.getRateList().stream().map(this::createRate).collect(Collectors.toList()));
    }

    private PersonalRate createRate(RateDto source) {
        PersonalRate rate = new PersonalRate();

        rate.setId(source.getId());
        rate.setValue(source.getValue());

        return rate;
    }

    private RateDto createRateDto(PersonalRate source) {
        Rate rate = source.getRate();
        RateDto rateDto = new RateDto();

        rateDto.setId(source.getId());
        rateDto.setMark(rate.getMark());
        rateDto.setPossibleRateValues(rate.getPossibleRateValues().stream()
                .map(i -> new PossibleRateDto(i.getName())).collect(Collectors.toList()));
        rateDto.setName(rate.getName());
        rateDto.setValue(source.getValue());

        return rateDto;
    }
}
