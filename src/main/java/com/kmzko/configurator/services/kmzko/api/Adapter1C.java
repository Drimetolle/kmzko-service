package com.kmzko.configurator.services.kmzko.api;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.domains.OptionalDetail;
import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.domains.conveyor.Detail;
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
    public List<Conveyor> getNearConveyors(List<Rate> rates) {
        Map<String, Object> map = rates.stream()
                .collect(Collectors.toMap(Rate::getName, Rate::getValue));
        List<Map<String, Object>> response = api.getNearConveyors(map);
        return constructConveyorList(response);
    }

    @Override
    public Conveyor getConveyorById(long id) {
        return constructConveyor(api.getConveyorById(id));
    }

    @Override
    public Detail getDetailById(long id) {
        return null;
    }

    @Override
    public List<OptionalDetail> getOptionsByType(ConveyorType type) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType detailType = mapper.getTypeFactory().
                constructCollectionType(List.class, OptionalDetail.class);
        return mapper.convertValue(api.getOptions(), detailType);
    }

    @Override
    public Object getCompatibilityDetails(List<Detail> details, Detail detail) {
        return null;
    }

    private List<Conveyor> constructConveyorList(List<Map<String, Object>> rawConveyors) {
        return rawConveyors.stream().map(this::constructConveyor).collect(Collectors.toList());
    }

    private Conveyor constructConveyor(Map<String, Object> rawConveyor) {
        return factory.createByMap(rawConveyor);
    }
}
