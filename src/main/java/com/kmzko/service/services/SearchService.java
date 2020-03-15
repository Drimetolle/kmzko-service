package com.kmzko.service.services;

import com.kmzko.service.domains.conveyor.Conveyor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private KmzkoService kmzkoService;

    public List<Conveyor> getNearConveyors() {
        return kmzkoService.getNearConveyors();
    }
}
