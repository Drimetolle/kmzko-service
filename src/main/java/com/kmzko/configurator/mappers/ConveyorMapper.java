package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.dto.ConveyorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConveyorMapper extends AbstractMapper<Conveyor, ConveyorDto> {
    private final ModelMapper mapper;

    ConveyorMapper(ModelMapper mapper) {
        super(Conveyor.class, ConveyorDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ConveyorDto.class, Conveyor.class)
                .addMappings(m -> m.skip(Conveyor::setType)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(ConveyorDto source, Conveyor destination) {
        destination.setType(ConveyorType.safeValueOf(source.getType()));
    }
}
