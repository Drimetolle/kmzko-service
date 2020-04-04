package com.kmzko.configurator.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.domains.conveyor.*;
import com.kmzko.configurator.dto.ConveyorDto;
import com.kmzko.configurator.exeption.NotValidConveyorMapException;
import com.kmzko.configurator.mappers.ConveyorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConveyorFactory {
    @Autowired
    private ConveyorMapper conveyorMapper;

    public void createTemplateConveyor() {
        /* TODO */
    }

    public Conveyor createByMap(Map<String, Object> rawConveyor) throws NotValidConveyorMapException {
        ObjectMapper objectMapper = new ObjectMapper();
        ConveyorDto conveyorDto = objectMapper.convertValue(rawConveyor, ConveyorDto.class);

        return conveyorMapper.toEntity(conveyorDto);
    }
}
