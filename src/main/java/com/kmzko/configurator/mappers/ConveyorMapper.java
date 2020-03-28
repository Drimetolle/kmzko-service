package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.dto.ConveyorDto;
import org.springframework.stereotype.Component;

@Component
public class ConveyorMapper extends AbstractMapper<Conveyor, ConveyorDto> {
    ConveyorMapper() {
        super(Conveyor.class, ConveyorDto.class);
    }
}
