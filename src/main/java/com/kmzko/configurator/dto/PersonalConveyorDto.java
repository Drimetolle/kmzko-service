package com.kmzko.configurator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.kmzko.configurator.dto.conveyor.NodeDto;
import com.kmzko.configurator.dto.conveyor.OptionalDetailDto;
import com.kmzko.configurator.dto.user.UserDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalConveyorDto extends AbstractDto {
    private String name;

    private String type;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<NodeDto> nodes;

    @JsonIgnore
    private UserDto user;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<OptionalDetailDto> optionalDetails;
}
