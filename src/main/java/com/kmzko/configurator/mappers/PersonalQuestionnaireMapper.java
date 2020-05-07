package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.PersonalQuestionnaireDto;
import com.kmzko.configurator.dto.RateDto;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class PersonalQuestionnaireMapper extends AbstractMapper<PersonalQuestionnaire, PersonalQuestionnaireDto> {
    private final ModelMapper mapper;
    private final PersonalRateMapper rateMapper;

    PersonalQuestionnaireMapper(ModelMapper mapper, PersonalRateMapper rateMapper) {
        super(PersonalQuestionnaire.class, PersonalQuestionnaireDto.class);
        this.mapper = mapper;
        this.rateMapper = rateMapper;
    }

//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(PersonalQuestionnaireDto.class, PersonalQuestionnaire.class)
//                .addMappings(m -> m.skip(PersonalQuestionnaire::setRateList)).setPostConverter(toEntityConverter());
//    }
//
//    @Override
//    void mapSpecificFields(PersonalQuestionnaireDto source, PersonalQuestionnaire destination) {
//
//        for (RateDto rate : source.getRateList()) {
//            rate.setId(null);
//        }
//
//        destination.setRateList(source.getRateList().stream().map(rateMapper::toEntity).collect(Collectors.toList()));
//    }
}
