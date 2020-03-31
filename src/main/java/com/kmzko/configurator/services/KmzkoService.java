package com.kmzko.configurator.services;

import com.kmzko.configurator.domains.OptionalDetail;
import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.domains.conveyor.Detail;
import com.kmzko.configurator.services.kmzko.api.AdapterAPI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class KmzkoService {
    private final AdapterAPI adapter;
    private final SearchService searchService;

    public KmzkoService(AdapterAPI adapter, SearchService searchService) {
        this.adapter = adapter;
        this.searchService = searchService;
    }

    public List<Conveyor> getNearConveyors(List<Rate> rates) {
        return searchService.getNearConveyors(rates);
    }

    public Conveyor getConveyorById(long id) {
        return adapter.getConveyorById(id);
    }

    public List<OptionalDetail> getOptions(ConveyorType type) {
        return adapter.getOptionsByType(type);
    }
}
