package com.kmzko.configurator.dto.conveyor;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeDto extends AbstractDto {
    private String name;
    private List<DetailDto> details;
}
