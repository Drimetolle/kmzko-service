package com.kmzko.service;

import com.kmzko.service.domains.conveyor.Characteristic;
import com.kmzko.service.domains.conveyor.Kilogram;
import com.kmzko.service.utils.ConveyorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacteristicTest {
    @Test
    public void as() {
        Characteristic ch = new Characteristic("12", new Kilogram(1));

    }
}
