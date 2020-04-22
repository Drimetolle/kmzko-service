package com.kmzko.configurator.mappers;

import com.kmzko.configurator.entity.user.OptionalDetail;
import com.kmzko.configurator.dto.OptionalDetailDto;
import org.springframework.stereotype.Component;

@Component
public class OptionalDetailMapper extends AbstractMapper<OptionalDetail, OptionalDetailDto> {
    public OptionalDetailMapper() {
        super(OptionalDetail.class, OptionalDetailDto.class);
    }
}
