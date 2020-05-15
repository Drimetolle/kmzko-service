package com.kmzko.configurator.services.kmzko.api;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.dto.conveyor.ConveyorDto;
import com.kmzko.configurator.dto.conveyor.DetailDto;
import com.kmzko.configurator.dto.conveyor.OptionalDetailDto;
import com.kmzko.configurator.mappers.ConveyorMapper;
import com.kmzko.configurator.utils.ConveyorFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Adapter1C implements AdapterAPI {
    private final Request1CAPI api;
    private final ConveyorFactory factory;
    private final ConveyorMapper mapper;

    public Adapter1C(Request1CAPI api, ConveyorFactory factory, ConveyorMapper mapper) {
        this.api = api;
        this.factory = factory;
        this.mapper = mapper;
    }

    @Override
    public List<ConveyorDto> getNearConveyors(List<Rate> rates) {
        Map<String, Object> map = rates.stream()
                .collect(Collectors.toMap(Rate::getName, Rate::getValue));
        List<Map<String, Object>> response = api.getNearConveyors(map);
        return constructConveyorList(response);
    }

    @Override
    public ConveyorDto getConveyorById(long id) {
        return constructConveyor(api.getConveyorById(id));
    }

    @Override
    public DetailDto getDetailById(long id) {
        return null;
    }

    @Override
    public List<OptionalDetailDto> getOptionsByType(ConveyorType type) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType detailType = mapper.getTypeFactory().
                constructCollectionType(List.class, OptionalDetailDto.class);
        return mapper.convertValue(api.getOptions(), detailType);
    }

    @Override
    public Object getCompatibilityDetails(List<DetailDto> details, DetailDto detail) {
        return null;
    }

    private List<ConveyorDto> constructConveyorList(List<Map<String, Object>> rawConveyors) {
        return rawConveyors.stream().map(this::constructConveyor).collect(Collectors.toList());
    }

    private ConveyorDto constructConveyor(Map<String, Object> rawConveyor) {
        return factory.createByMap(rawConveyor);
    }
}
