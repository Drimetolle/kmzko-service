package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.dto.questionnaire.PossibleRateDto;
import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import com.kmzko.configurator.dto.questionnaire.RateDto;
import com.kmzko.configurator.entity.user.questionnaire.TestP;
import com.kmzko.configurator.entity.user.questionnaire.TestQ;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class PersonalQuestionnaireToQuestionnaireDto extends AbstractMapper<TestQ, QuestionnaireDto> {
    private final ModelMapper mapper;

    public PersonalQuestionnaireToQuestionnaireDto(ModelMapper mapper) {
        super(TestQ.class, QuestionnaireDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(TestQ.class, QuestionnaireDto.class)
                .addMappings(m -> m.skip(QuestionnaireDto::setName)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(QuestionnaireDto::setType)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(QuestionnaireDto::setRateList)).setPostConverter(toDtoConverter());
    }

    @Override
    void mapSpecificFields(TestQ source, QuestionnaireDto destination) {
        Questionnaire questionnaire = source.getQuestionnaire();
        destination.setType(questionnaire.getType().getView());
        destination.setName(questionnaire.getName());

        destination.setRateList(source.getRateList().stream().map(this::createRate).collect(Collectors.toList()));
    }

    private RateDto createRate(TestP source) {
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
