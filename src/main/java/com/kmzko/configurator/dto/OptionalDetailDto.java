package com.kmzko.configurator.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionalDetailDto extends AbstractDto {
    private String name;
}
