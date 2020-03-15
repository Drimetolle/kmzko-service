package com.kmzko.service.services;

import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.utils.AdapterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KmzkoService {
    @Autowired
    private AdapterAPI adapter;

    public List<Conveyor> getNearConveyors() {
        return adapter.getNearConveyors();
    }
}
