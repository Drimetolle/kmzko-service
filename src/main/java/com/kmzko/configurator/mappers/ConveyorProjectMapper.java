package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.ConveyorProjectDto;
import com.kmzko.configurator.entity.user.ConveyorProject;
import org.springframework.stereotype.Component;

@Component
public class ConveyorProjectMapper extends AbstractMapper<ConveyorProject, ConveyorProjectDto> {
    public ConveyorProjectMapper() {
        super(ConveyorProject.class, ConveyorProjectDto.class);
    }
}
