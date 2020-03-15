package com.kmzko.service.services;

import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.utils.AdapterAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KmzkoService {
    private final AdapterAPI adapter;

    public KmzkoService(AdapterAPI adapter) {
        this.adapter = adapter;
    }

    public List<Conveyor> getNearConveyors() {
        return adapter.getNearConveyors();
    }
}
