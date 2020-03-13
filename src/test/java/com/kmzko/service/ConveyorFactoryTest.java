package com.kmzko.service;

import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.domains.conveyor.Kilogram;
import com.kmzko.service.utils.ConveyorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConveyorFactoryTest {
    @Autowired
    private ConveyorFactory factory;

    @Test
    public void as() {
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

        Conveyor conveyor = factory.createByMap(result);
    }
}
