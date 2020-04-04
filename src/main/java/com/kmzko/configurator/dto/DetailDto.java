package com.kmzko.configurator.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetailDto extends AbstractDto {
    private String name;
    private int count;
    private List<CharacteristicDto> characteristics;
}
