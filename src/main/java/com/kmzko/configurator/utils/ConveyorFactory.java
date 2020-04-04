package com.kmzko.configurator.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.dto.ConveyorDto;
import com.kmzko.configurator.exeption.NotValidConveyorMapException;
import com.kmzko.configurator.mappers.ConveyorMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConveyorFactory {
    private final ConveyorMapper conveyorMapper;

    public ConveyorFactory(ConveyorMapper conveyorMapper) {
        this.conveyorMapper = conveyorMapper;
    }

    public Conveyor createByMap(Map<String, Object> rawConveyor) throws NotValidConveyorMapException {
        ObjectMapper objectMapper = new ObjectMapper();
        ConveyorDto conveyorDto = objectMapper.convertValue(rawConveyor, ConveyorDto.class);

        return conveyorMapper.toEntity(conveyorDto);
    }
}
