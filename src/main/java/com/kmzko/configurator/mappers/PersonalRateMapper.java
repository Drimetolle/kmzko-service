package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.questionnaire.RateDto;
import com.kmzko.configurator.entity.user.questionnaire.PersonalRate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PersonalRateMapper extends AbstractMapper<PersonalRate, RateDto> {
    private final ModelMapper mapper;

    PersonalRateMapper(ModelMapper mapper) {
        super(PersonalRate.class, RateDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(PersonalRate.class, RateDto.class)
                .addMappings(m -> m.skip(RateDto::setMark)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(RateDto::setName)).setPostConverter(toDtoConverter());
    }

    @Override
    void mapSpecificFields(PersonalRate source, RateDto destination) {
        destination.setMark(source.getRate().getMark());
        destination.setName(source.getRate().getName());
    }
}
