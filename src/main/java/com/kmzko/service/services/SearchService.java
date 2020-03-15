package com.kmzko.service.services;

import com.kmzko.service.domains.conveyor.Conveyor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final KmzkoService kmzkoService;

    public SearchService(KmzkoService kmzkoService) {
        this.kmzkoService = kmzkoService;
    }

    public List<Conveyor> getNearConveyors() {
        return kmzkoService.getNearConveyors();
    }
}
