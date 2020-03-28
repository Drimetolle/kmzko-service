package com.kmzko.configurator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.entity.user.PersonalConveyor;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.repositories.ConveyorRepo;
import com.kmzko.configurator.repositories.PersonalConveyorRepo;
import com.kmzko.configurator.repositories.UserRepo;
import com.kmzko.configurator.utils.ConveyorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CascadeDeleteIntegrationTest {
    @Autowired
    private PersonalConveyorRepo personalConveyorRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ConveyorRepo conveyorRepo;
    @Autowired
    private ConveyorFactory conveyorFactory;
    //    private final String json = "{\"id\":1,\"name\":\"Ленточного конвейера типа УКЛС\",\"type\":\"TAPE\",\"utilDate\":\"2020-03-24T14:17:23.639+0000\",\"rateList\":[{\"id\":2,\"name\":\"Ширина ленты\",\"value\":\"\",\"mark\":\"tape-width\"},{\"id\":3,\"name\":\"Длина конвейера\",\"value\":\"\",\"mark\":\"tape-length\"},{\"id\":4,\"name\":\"Производительность\",\"value\":\"\",\"mark\":\"productivity\"},{\"id\":5,\"name\":\"Угол наклона\",\"value\":\"\",\"mark\":\"tilt-angle\"},{\"id\":6,\"name\":\"Скорость ленты\",\"value\":\"\",\"mark\":\"tape-speed\"},{\"id\":7,\"name\":\"Насыпная масса\",\"value\":\"\",\"mark\":\"bulk\"},{\"id\":8,\"name\":\"Гранулометрический состав\",\"value\":\"\",\"mark\":\"grading\"},{\"id\":9,\"name\":\"Состав фракции\",\"value\":\"\",\"mark\":\"fraction\"},{\"id\":10,\"name\":\"Содержание влаги\",\"value\":\"\",\"mark\":\"moisture\"},{\"id\":11,\"name\":\"Температура перевозимого груза\",\"value\":\"\",\"mark\":\"temperature-cargo\"},{\"id\":12,\"name\":\"Система управления конвейером\",\"value\":\"\",\"mark\":\"conveyor-control-system\"},{\"id\":13,\"name\":\"Шеф-монтаж конвейера\",\"value\":\"\",\"mark\":\"installation-chief\"},{\"id\":14,\"name\":\"Диапазон температуры окружающего воздуха\",\"value\":\"\",\"mark\":\"temperature-range\"},{\"id\":15,\"name\":\"Влажность окружающего воздуха\",\"value\":\"\",\"mark\":\"humidity-air\"},{\"id\":16,\"name\":\"Содержание пыли в зоне расположения приводного барабана\",\"value\":\"\",\"mark\":\"dust\"}]}";

    @Test
    public void deletePersonalConveyorNotEffectToNodes() throws Exception {
        final String json = "{\"name\":\"Конвейер ленточный\",\"type\":\"TAPE\",\"nodes\":[{\"name\":\"Лента\",\"details\":[{\"name\":\"Конвейерная лента\",\"characteristics\":[{\"name\":\"Ширина ленты\",\"value\":\"2\",\"mark\":\"tape-width\",\"type\":\"meter\"},{\"name\":\"Длина ленты\",\"value\":\"50\",\"mark\":\"tape-length\",\"type\":\"meter\"}]}]},{\"name\":\"Приводная станция\",\"details\":[{\"name\":\"Приводной барабан\",\"characteristics\":[{\"name\":\"Ширина ленты\",\"value\":\"530\",\"mark\":\"\",\"type\":\"meter\"}]},{\"name\":\"Барабан отклоняющий\",\"characteristics\":[{\"name\":\"Ширина ленты\",\"value\":\"325\",\"mark\":\"\",\"type\":\"meter\"}]}]}]}";
        final String pJson = "{\"name\":\"Конвейер ленточный\",\"type\":\"TAPE\",\"nodes\":[{\"name\":\"Лента\",\"details\":[{\"name\":\"Конвейерная лента\",\"characteristics\":[{\"name\":\"Ширина ленты\",\"value\":\"2\",\"mark\":\"tape-width\",\"type\":\"meter\"},{\"name\":\"Длина ленты\",\"value\":\"50\",\"mark\":\"tape-length\",\"type\":\"meter\"}]}]},{\"name\":\"Приводная станция\",\"details\":[{\"name\":\"Приводной барабан\",\"characteristics\":[{\"name\":\"Ширина ленты\",\"value\":\"530\",\"mark\":\"\",\"type\":\"meter\"}]},{\"name\":\"Барабан отклоняющий\",\"characteristics\":[{\"name\":\"Ширина ленты\",\"value\":\"325\",\"mark\":\"\",\"type\":\"meter\"}]}]}]}";

        User user = new User();
        user.setEmail("123");
        User saveU = userRepo.save(user);
        ObjectMapper mapper = new ObjectMapper();

        Conveyor conveyor = mapper.readValue(json, Conveyor.class);
        PersonalConveyor personalConveyor = mapper.readValue(pJson, PersonalConveyor.class);
        personalConveyor.setUser(saveU);
        conveyorRepo.save(conveyor);
        personalConveyorRepo.save(personalConveyor);

        personalConveyorRepo.deleteAll();

        List<Conveyor> conveyors = conveyorRepo.findAll();

        assertThat(conveyors.size()).isEqualTo(1);
        assertThat(conveyors.get(0).getNodes().size()).isEqualTo(2);
    }
}
