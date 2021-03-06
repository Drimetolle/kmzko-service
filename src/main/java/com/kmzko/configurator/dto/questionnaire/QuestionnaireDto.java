package com.kmzko.configurator.dto.questionnaire;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDto extends AbstractDto {
    private String name;
    private String type;
    private List<RateDto> rateList;
}
