package com.kmzko.configurator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalQuestionnaireDto extends AbstractDto {
    private String name;
    private String type;
    private List<RateDto> rateList;
    @JsonIgnore
    private UserDto user;
}
