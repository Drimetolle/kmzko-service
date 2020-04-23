package com.kmzko.configurator.services;

import com.kmzko.configurator.entity.user.conveyor.OptionalDetail;
import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.domains.conveyor.Conveyor;
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
