package com.kmzko.configurator.mappers;

import com.kmzko.configurator.domains.conveyor.Detail;
import com.kmzko.configurator.dto.conveyor.DetailDto;
import org.springframework.stereotype.Component;

@Component
public class DetailMapper extends AbstractMapper<Detail, DetailDto> {
    public DetailMapper() {
        super(Detail.class, DetailDto.class);
    }
}
