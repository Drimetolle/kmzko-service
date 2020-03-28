package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.AbstractDto;
import com.kmzko.configurator.entity.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {
    E toEntity(D dto);
    D toDto(E entity);
}
