package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.questionnaire.RateDto;
import com.kmzko.configurator.entity.user.questionnaire.PersonalRate;
import org.springframework.stereotype.Component;

@Component
public class PersonalRateMapper extends AbstractMapper<PersonalRate, RateDto> {
    PersonalRateMapper() {
        super(PersonalRate.class, RateDto.class);
    }
}
