package com.kmzko.configurator.dto.conveyor;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionalDetailDto extends AbstractDto {
    private String name;
}
