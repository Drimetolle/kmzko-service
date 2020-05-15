package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.dto.questionnaire.QuestionnaireDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class QuestionnaireMapper extends AbstractMapper<Questionnaire, QuestionnaireDto> {
    private final ModelMapper mapper;

    public QuestionnaireMapper(ModelMapper mapper) {
        super(Questionnaire.class, QuestionnaireDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(QuestionnaireDto.class, Questionnaire.class)
                .addMappings(m -> m.skip(Questionnaire::setType)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(QuestionnaireDto source, Questionnaire destination) {
        destination.setType(ConveyorType.safeValueOf(source.getType()));
    }
}
