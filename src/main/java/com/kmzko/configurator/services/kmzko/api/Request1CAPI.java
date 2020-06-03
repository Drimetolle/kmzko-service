package com.kmzko.configurator.services.kmzko.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.domains.conveyor.Unit;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Request1CAPI {
    public List<Map<String, Object>> getNearConveyors(Map<String, Object> payload) {
        String json = "[{\"id\":0,\"name\":\"Конвейер ленточный\",\"nodes\":[{\"id\":0,\"name\":\"Лента\",\"details\":[{\"id\":0,\"name\":\"Конвейерная лента\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"2\",\"mark\":\"tape-width\",\"unit\":{\"name\":\"meter\"}},{\"id\":0,\"name\":\"Длина ленты\",\"value\":\"50\",\"mark\":\"tape-length\",\"unit\":{\"name\":\"meter\"}}]}]},{\"id\":1,\"name\":\"Приводная станция\",\"details\":[{\"id\":0,\"name\":\"Приводной барабан\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"530\",\"mark\":\"\",\"unit\":{\"name\":\"meter\"}}]},{\"id\":1,\"name\":\"Барабан отклоняющий\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"325\",\"mark\":\"\",\"unit\":{\"name\":\"meter\"}}]}]}]},{\"id\":1,\"name\":\"Конвейер ленточный 2\",\"nodes\":[{\"id\":1,\"name\":\"Лента\",\"details\":[{\"id\":1,\"name\":\"Конвейерная лента\",\"characteristics\":[{\"id\":1,\"name\":\"Ширина ленты\",\"value\":\"2\",\"mark\":\"tape-width\",\"unit\":{\"name\":\"meter\"}},{\"id\":1,\"name\":\"Длина ленты\",\"value\":\"50\",\"mark\":\"tape-length\",\"unit\":{\"name\":\"meter\"}}]}]}]}]";

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> map = new ArrayList<>();
        try {
            map = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return map;
    }

    public List<Object> getOptions() {
        String json = "[{\"id\":0,\"name\":\"Конвейерная лента\",\"characteristics\":[{\"id\":0,\"name\":\"Ширина ленты\",\"value\":\"2\",\"mark\":\"tape-width\",\"unit\":{\"name\":\"meter\"}},{\"id\":0,\"name\":\"Длина ленты\",\"value\":\"50\",\"mark\":\"tape-length\",\"unit\":{\"name\":\"meter\"}}]}]";

        ObjectMapper mapper = new ObjectMapper();
        List<Object> map = new ArrayList<>();
        try {
            map = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return map;
    }

    public Map<String, Object> getConveyorById(long id) {
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> node = new HashMap<>();
        List<Map<String, Object>> nodes = Arrays.asList(node);

        Map<String, Object> detail = new HashMap<>();
        List<Map<String, Object>> details = Arrays.asList(detail);

        Map<String, Object> characteristic = new HashMap<>();
        List<Map<String, Object>> characteristics = Arrays.asList(characteristic);

        characteristic.put("name", "Rate1");
        characteristic.put("value", "123");
        characteristic.put("unit", new Unit("kilo"));

        detail.put("characteristics", characteristics);
        detail.put("name", "detail1");

        node.put("details", details);
        node.put("name", "node1");

        result.put("nodes", nodes);
        result.put("name", "Конвейер1");

        return result;
    }
}
