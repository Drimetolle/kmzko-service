package com.kmzko.configurator.services;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.dto.conveyor.ConveyorDto;
import com.kmzko.configurator.dto.conveyor.OptionalDetailDto;
import com.kmzko.configurator.dto.questionnaire.RateDto;
import com.kmzko.configurator.services.kmzko.api.AdapterAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KmzkoService {
    private final AdapterAPI adapter;
    private final SearchService searchService;

    public KmzkoService(AdapterAPI adapter, SearchService searchService) {
        this.adapter = adapter;
        this.searchService = searchService;
    }

    public List<ConveyorDto> getNearConveyors(List<RateDto> rates) {
        return searchService.getNearConveyors(rates);
    }

    public ConveyorDto getConveyorById(long id) {
        return adapter.getConveyorById(id);
    }

    public List<OptionalDetailDto> getOptions(ConveyorType type) {
        return adapter.getOptionsByType(type);
    }
}
