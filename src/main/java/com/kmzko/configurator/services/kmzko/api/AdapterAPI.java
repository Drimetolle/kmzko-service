package com.kmzko.configurator.services.kmzko.api;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.dto.conveyor.ConveyorDto;
import com.kmzko.configurator.dto.conveyor.DetailDto;
import com.kmzko.configurator.dto.conveyor.OptionalDetailDto;

import java.util.List;

public interface AdapterAPI {
    List<ConveyorDto> getNearConveyors(List<Rate> rates);
    ConveyorDto getConveyorById(long id);
    DetailDto getDetailById(long id);
    List<OptionalDetailDto> getOptionsByType(ConveyorType type);
    Object getCompatibilityDetails(List<DetailDto> details, DetailDto detail);
}
