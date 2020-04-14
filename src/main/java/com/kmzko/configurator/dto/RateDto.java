package com.kmzko.configurator.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDto extends AbstractDto {
    private String name;
    private String value;
    private String mark;
}
