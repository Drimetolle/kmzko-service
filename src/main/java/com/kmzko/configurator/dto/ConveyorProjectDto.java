package com.kmzko.configurator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConveyorProjectDto extends AbstractDto {
    private PersonalConveyorDto conveyor;
    private PersonalQuestionnaireDto questionnaire;
}
