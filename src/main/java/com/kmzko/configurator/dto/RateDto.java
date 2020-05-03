package com.kmzko.configurator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDto extends AbstractDto {
    private String name;
    private String value;
    private String mark;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PossibleRateDto> possibleRateValues;
}
