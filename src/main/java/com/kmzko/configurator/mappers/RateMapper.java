package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.dto.questionnaire.RateDto;
import org.springframework.stereotype.Component;

@Component
public class RateMapper extends AbstractMapper<Rate, RateDto> {
    public RateMapper() {
        super(Rate.class, RateDto.class);
    }
}
