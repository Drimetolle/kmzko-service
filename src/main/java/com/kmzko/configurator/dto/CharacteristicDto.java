package com.kmzko.configurator.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacteristicDto extends AbstractDto {
    private String name;
    private String value;
    private String mark;
    private String type;
}
