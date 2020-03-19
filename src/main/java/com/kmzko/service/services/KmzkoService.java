package com.kmzko.service.services;

import com.kmzko.service.domains.Rate;
import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.utils.AdapterAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KmzkoService {
    private final AdapterAPI adapter;
    private final SearchService searchService;

    public KmzkoService(AdapterAPI adapter, SearchService searchService1) {
        this.adapter = adapter;
        this.searchService = searchService1;
    }

    public List<Conveyor> getNearConveyors(List<Rate> rates) {
        return searchService.getNearConveyors(rates);
    }

    public Conveyor getConveyorById(long id) {
        return adapter.getConveyorById(id);
    }

}
