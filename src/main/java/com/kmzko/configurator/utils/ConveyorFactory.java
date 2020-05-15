package com.kmzko.configurator.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.dto.conveyor.ConveyorDto;
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

    public ConveyorDto createByMap(Map<String, Object> rawConveyor) throws NotValidConveyorMapException {
        ObjectMapper objectMapper = new ObjectMapper();
        ConveyorDto conveyorDto = objectMapper.convertValue(rawConveyor, ConveyorDto.class);

        return conveyorDto;
    }
}
