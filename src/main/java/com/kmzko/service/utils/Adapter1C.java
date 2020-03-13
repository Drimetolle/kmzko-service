package com.kmzko.service.utils;

import com.kmzko.service.domains.conveyor.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class Adapter1C implements AdapterAPI {
    @Autowired
    private Request1CAPI api;

    @Autowired
    private ConveyorFactory factory;

    @Override
    public List<Conveyor> getNearConveyors() {
        List<Map<String, Object>> response = api.getNearConveyors();
        return constructConveyorList(response);
    }

    private List<Conveyor> constructConveyorList(List<Map<String, Object>> rawConveyors) {
        return rawConveyors.stream().map(this::constructConveyor).collect(Collectors.toList());
    }

    private Conveyor constructConveyor(Map<String, Object> rawConveyor) {
        return factory.createByMap(rawConveyor);
    }
}
