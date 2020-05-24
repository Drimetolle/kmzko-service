package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.ConveyorProjectDto;
import com.kmzko.configurator.entity.user.ConveyorProject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConveyorProjectMapper extends AbstractMapper<ConveyorProject, ConveyorProjectDto> {
    private final ModelMapper mapper;
    private final PersonalQuestionnaireToQuestionnaireDtoMapper questionnaireDtoMapper;

    public ConveyorProjectMapper(ModelMapper mapper, PersonalQuestionnaireToQuestionnaireDtoMapper questionnaireDtoMapper) {
        super(ConveyorProject.class, ConveyorProjectDto.class);
        this.mapper = mapper;
        this.questionnaireDtoMapper = questionnaireDtoMapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ConveyorProject.class, ConveyorProjectDto.class)
                .addMappings(m -> m.skip(ConveyorProjectDto::setQuestionnaire)).setPostConverter(toDtoConverter());
    }

    @Override
    void mapSpecificFields(ConveyorProject source, ConveyorProjectDto destination) {
        Object lol = source.getQuestionnaire();
        Object lol1 = questionnaireDtoMapper.toDto(source.getQuestionnaire());

        destination.setQuestionnaire(questionnaireDtoMapper.toDto(source.getQuestionnaire()));
    }
}
