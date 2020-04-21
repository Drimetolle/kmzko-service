package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.RateDto;
import com.kmzko.configurator.entity.user.PersonalRate;
import org.springframework.stereotype.Component;

@Component
public class PersonalRateMapper extends AbstractMapper<PersonalRate, RateDto> {
    PersonalRateMapper() {
        super(PersonalRate.class, RateDto.class);
    }
}
