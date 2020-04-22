package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.PersonalConveyorDto;
import com.kmzko.configurator.entity.user.conveyor.PersonalConveyor;
import org.springframework.stereotype.Component;

@Component
public class PersonalConveyorMapper extends AbstractMapper<PersonalConveyor, PersonalConveyorDto> {
    public PersonalConveyorMapper() {
        super(PersonalConveyor.class, PersonalConveyorDto.class);
    }
}
