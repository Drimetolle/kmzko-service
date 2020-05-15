package com.kmzko.configurator.dto.conveyor;

import com.kmzko.configurator.dto.AbstractDto;
import com.kmzko.configurator.dto.conveyor.NodeDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConveyorDto extends AbstractDto {
    private String name;
    private String type;
    private List<NodeDto> nodes;
    private String img;
}
