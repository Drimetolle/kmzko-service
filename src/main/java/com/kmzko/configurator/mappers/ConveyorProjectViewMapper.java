package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.readonly.ConveyorProjectPreviewDto;
import com.kmzko.configurator.entity.user.ConveyorProject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConveyorProjectViewMapper extends AbstractMapper<ConveyorProject, ConveyorProjectPreviewDto> {
    private final ModelMapper mapper;

    public ConveyorProjectViewMapper(ModelMapper mapper) {
        super(ConveyorProject.class, ConveyorProjectPreviewDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ConveyorProject.class, ConveyorProjectPreviewDto.class)
                .addMappings(m -> m.skip(ConveyorProjectPreviewDto::setTitle)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ConveyorProjectPreviewDto::setConveyor)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ConveyorProjectPreviewDto::setQuestionnaire)).setPostConverter(toDtoConverter());
    }

    @Override
    void mapSpecificFields(ConveyorProject source, ConveyorProjectPreviewDto destination) {
        destination.setTitle(source.getQuestionnaire().getName());
        destination.setConveyor(!source.getConveyor().getNodes().isEmpty());
        destination.setQuestionnaire(!source.getQuestionnaire().getRateList().isEmpty());
    }
}
