package com.kmzko.configurator.dto.conveyor;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacteristicDto extends AbstractDto {
    private String name;
    private String value;
    private String mark;
    private String type;
}
