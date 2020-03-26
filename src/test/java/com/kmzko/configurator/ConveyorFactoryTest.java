package com.kmzko.configurator;

import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.utils.ConveyorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.*;

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
    public void defaultBehavior() {
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

        assertThat(conveyor).isNotNull();
        assertThat(conveyor.getName()).isEqualTo("Конвейер1");
        assertThat(conveyor.getNodes().size()).isEqualTo(1);
        assertThat(conveyor.getNodes().get(0).getName()).isEqualTo("node1");
        assertThat(conveyor.getNodes().get(0).getDetails().size()).isEqualTo(1);
        assertThat(conveyor.getNodes().get(0).getDetails().get(0).getName()).isEqualTo("detail1");
        assertThat(conveyor.getNodes().get(0).getDetails().get(0).getCharacteristics().size()).isEqualTo(1);
        assertThat(conveyor.getNodes().get(0).getDetails().get(0).getCharacteristics().get(0).getValue()).isEqualTo("123");
    }

}
