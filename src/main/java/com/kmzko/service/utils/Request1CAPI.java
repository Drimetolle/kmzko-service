package com.kmzko.service.utils;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Request1CAPI {
    public List<Map<String, Object>> getNearConveyors(Map<String, Object> payload) {
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> node = new HashMap<>();
        List<Map<String, Object>> nodes = Collections.singletonList(node);

        Map<String, Object> detail = new HashMap<>();
        List<Map<String, Object>> details = Collections.singletonList(detail);

        Map<String, Object> characteristic1 = new HashMap<>();
        Map<String, Object> characteristic2 = new HashMap<>();
        List<Map<String, Object>> characteristics = Arrays.asList(characteristic1, characteristic2);

        characteristic1.put("name", "Ширина ленты");
        characteristic1.put("value", "2");
        characteristic1.put("type", "meter");
        characteristic1.put("mark", "tape-width");
        characteristic2.put("name", "Длина ленты");
        characteristic2.put("value", "50");
        characteristic2.put("type", "meter");
        characteristic2.put("mark", "tape-length");

        detail.put("characteristics", characteristics);
        detail.put("name", "Конвейерная лента");

        node.put("details", details);
        node.put("name", "Лента");

        result.put("nodes", nodes);
        result.put("name", "Конвейер ленточный");

        return Collections.singletonList(result);
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
        characteristic.put("type", "kilo");

        detail.put("characteristics", characteristics);
        detail.put("name", "detail1");

        node.put("details", details);
        node.put("name", "node1");

        result.put("nodes", nodes);
        result.put("name", "Конвейер1");

        return result;
    }
}
