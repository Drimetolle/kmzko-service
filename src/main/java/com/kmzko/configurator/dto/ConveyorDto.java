package com.kmzko.configurator.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConveyorDto extends AbstractDto {
    private String name;
    private String type;
    private List<NodeDto> nodes;
    private String img;
}
