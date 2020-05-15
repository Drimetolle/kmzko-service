package com.kmzko.configurator.dto.questionnaire;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PossibleRateDto extends AbstractDto {
    private String name;
}
