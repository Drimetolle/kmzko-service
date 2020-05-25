package com.kmzko.configurator.dto.conveyor;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDto extends AbstractDto {
    private String name;
    private List<CharacteristicDto> characteristics;
}
