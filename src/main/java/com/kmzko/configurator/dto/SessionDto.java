package com.kmzko.configurator.dto;

import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto extends AbstractDto {
    private PersonalQuestionnaire questionnaire;
    private PersonalConveyorDto conveyor;
}
