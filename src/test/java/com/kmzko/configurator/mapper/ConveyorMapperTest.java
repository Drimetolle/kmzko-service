package com.kmzko.configurator.mapper;

import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.conveyor.Node;
import com.kmzko.configurator.dto.ConveyorDto;
import com.kmzko.configurator.dto.NodeDto;
import com.kmzko.configurator.mappers.ConveyorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConveyorMapperTest {
    @Autowired
    private ConveyorMapper mapper;

    @Test
    public void defaultBehaviorEntityToDto() {
        List<Node> nodeList = new ArrayList<>();
        Conveyor conveyor = new Conveyor("C1", ConveyorType.TAPE, nodeList);
        conveyor.setId(Long.valueOf(1));

        ConveyorDto personalQuestionnaire = mapper.toDto(conveyor);

        assertThat(personalQuestionnaire).isNotNull();
        assertThat(personalQuestionnaire.getId()).isEqualTo(conveyor.getId());
        assertThat(personalQuestionnaire.getName()).isEqualTo(conveyor.getName());
        assertThat(personalQuestionnaire.getType()).isEqualTo(conveyor.getType().toString());
        assertThat(personalQuestionnaire.getNodes()).isEqualTo(nodeList);
    }

    @Test
    public void defaultBehaviorDtoToEntity() {
        List<NodeDto> nodeList = new ArrayList<>();
        ConveyorDto conveyorDto = new ConveyorDto("Q1", "TAPE", nodeList, "https://url.com");
        conveyorDto.setId(Long.valueOf(1));

        Conveyor questionnaire = mapper.toEntity(conveyorDto);

        assertThat(questionnaire).isNotNull();
        assertThat(conveyorDto.getId()).isEqualTo(questionnaire.getId());
        assertThat(conveyorDto.getName()).isEqualTo(questionnaire.getName());
        assertThat(conveyorDto.getType()).isEqualTo(questionnaire.getType().toString());
        assertThat(conveyorDto.getNodes()).isEqualTo(nodeList);
    }

    @Test
    public void checkCaseType() {
        List<NodeDto> nodeList = new ArrayList<>();
        ConveyorDto personalQuestionnaire = new ConveyorDto("Q1", "TaPe", nodeList, "https://url.com");
        personalQuestionnaire.setId(Long.valueOf(1));

        Conveyor questionnaire = mapper.toEntity(personalQuestionnaire);

        assertThat(questionnaire).isNotNull();
        assertThat(personalQuestionnaire.getId()).isEqualTo(questionnaire.getId());
        assertThat(personalQuestionnaire.getName()).isEqualTo(questionnaire.getName());
        assertThat(personalQuestionnaire.getType().toUpperCase()).isEqualTo(questionnaire.getType().toString());
        assertThat(personalQuestionnaire.getNodes()).isEqualTo(nodeList);
    }
}
