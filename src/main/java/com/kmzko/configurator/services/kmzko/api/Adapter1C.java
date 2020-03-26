package com.kmzko.configurator.services.kmzko.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.Rate;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.domains.conveyor.Detail;
import com.kmzko.configurator.utils.ConveyorFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Adapter1C implements AdapterAPI {
    private final Request1CAPI api;
    private final ConveyorFactory factory;

    public Adapter1C(Request1CAPI api, ConveyorFactory factory) {
        this.api = api;
        this.factory = factory;
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
    public List<Detail> getOptionsByType(ConveyorType type) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(api.getOptions(), List.class);
    }

    @Override
    public Object getCompatibilityDetails(List<Detail> details, Detail detail) {
        return null;
    }

    private List<Conveyor> constructConveyorList(List<Map<String, Object>> rawConveyors) {
        return rawConveyors.stream().map(this::constructConveyor).collect(Collectors.toList());
    }

    private Conveyor constructConveyor(Map<String, Object> rawConveyor) {
//        return factory.createByMap(rawConveyor);
        return new ObjectMapper().convertValue(rawConveyor, Conveyor.class);
    }
}
