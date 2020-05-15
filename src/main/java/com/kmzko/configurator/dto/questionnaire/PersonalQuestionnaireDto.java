package com.kmzko.configurator.dto.questionnaire;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kmzko.configurator.dto.AbstractDto;
import com.kmzko.configurator.dto.user.UserDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalQuestionnaireDto extends AbstractDto {
    private String name;
    private String type;
    private List<RateDto> rateList;
    @JsonIgnore
    private UserDto user;
}
