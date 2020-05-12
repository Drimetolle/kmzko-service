package com.kmzko.configurator.dto.readonly;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConveyorProjectPreviewDto extends AbstractDto {
    private String title;
    private boolean conveyor;
    private boolean questionnaire;
}
