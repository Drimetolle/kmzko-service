package com.kmzko.service.utils;

import com.kmzko.service.domains.conveyor.Kilogram;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Request1CAPI {
    public List<Map<String, Object>> getNearConveyors() {
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

        return Collections.singletonList(result);
    }
}
