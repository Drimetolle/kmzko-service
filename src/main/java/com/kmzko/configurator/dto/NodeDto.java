package com.kmzko.configurator.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NodeDto extends AbstractDto {
    private String name;
    private List<DetailDto> details;
}
