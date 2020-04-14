package com.kmzko.configurator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalConveyorDto extends AbstractDto {
    private String name;
    private String type;
    private List<NodeDto> nodes;
    @JsonIgnore
    private UserDto user;
    private List<OptionalDetailDto> optionalDetails;
}
