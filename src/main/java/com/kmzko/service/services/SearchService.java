package com.kmzko.service.services;

import com.kmzko.service.domains.Rate;
import com.kmzko.service.domains.conveyor.Conveyor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final KmzkoService kmzkoService;

    public SearchService(KmzkoService kmzkoService) {
        this.kmzkoService = kmzkoService;
    }

    public List<Conveyor> getNearConveyors(List<Rate> rates) {
        return kmzkoService.getNearConveyors(rates);
    }
    public Conveyor getConveyorById(long id) {
        return kmzkoService.getConveyorById(id);
    }
}
