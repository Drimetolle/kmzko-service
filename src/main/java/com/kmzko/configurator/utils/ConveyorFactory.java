package com.kmzko.configurator.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.domains.conveyor.*;
import com.kmzko.configurator.exeption.NotValidConveyorMapException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConveyorFactory {
    public void createTemplateConveyor() {
        /* TODO */
    }

    public Conveyor createByMap(Map<String, Object> rawConveyor) throws NotValidConveyorMapException {
        return new ObjectMapper().convertValue(rawConveyor, Conveyor.class);
    }

    public boolean validMap(Map<String, Object> rawConveyor) {
        return createConveyor(rawConveyor).getNodes() != null;
    }

    private Conveyor createConveyor(Map<String, Object> rawConveyor) {
        return new Conveyor((String) rawConveyor.get("name"),
                createNode((List<Map<String, Object>>)rawConveyor.get("nodes")));
    }

    private List<Node> createNode(List<Map<String, Object>> nodes) {
        return nodes.stream().map(i ->
                new Node((String) i.get("name"),
                        createDetail((List<Map<String, Object>>) i.get("details"))))
                .collect(Collectors.toList());
    }

    private List<Detail> createDetail(List<Map<String, Object>> details) {
        return details.stream().map(i ->
                new Detail((String) i.get("name"),
                        createCharacteristic((List<Map<String, Object>>) i.get("characteristics"))))
                .collect(Collectors.toList());
    }

    private List<Characteristic> createCharacteristic(List<Map<String, Object>> characteristics) {
        return characteristics.stream().map(i -> {
            Unit u = CharacteristicByStringType((String) i.get("value"));
            // TODO VERY IMPORTENT
            return new Characteristic((String) i.get("name"), (String) i.get("mark"), (String) i.get("value"), (String) i.get("type"));
        }).collect(Collectors.toList());
    }

    private Unit CharacteristicByStringType(String type) {
        if (type.equals("kilo")) {
            return new Kilogram(Integer.parseInt(type));
        }
        else { //default
            return new Kilogram(Integer.parseInt(type));
        }
    }
}
