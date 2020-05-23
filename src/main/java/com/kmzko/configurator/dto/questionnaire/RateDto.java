package com.kmzko.configurator.dto.questionnaire;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.kmzko.configurator.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDto extends AbstractDto {
    private String name;
    private String value;
    private String mark;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<PossibleRateDto> possibleRateValues;
}
