package com.kmzko.configurator.services;

import com.kmzko.configurator.domains.Rate;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.repositories.ConveyorRepo;
import com.kmzko.configurator.services.kmzko.api.AdapterAPI;
import com.kmzko.configurator.utils.CompareConveyorAndQuestionnaire;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final AdapterAPI adapter;
    private final ConveyorRepo repository;
    private final CompareConveyorAndQuestionnaire compareConveyorAndQuestionnaire;

    public SearchService(AdapterAPI adapter, ConveyorRepo repository, CompareConveyorAndQuestionnaire compareConveyorAndQuestionnaire) {
        this.adapter = adapter;
        this.repository = repository;
        this.compareConveyorAndQuestionnaire = compareConveyorAndQuestionnaire;
    }

    public List<Conveyor> getNearConveyors(List<Rate> rates) {
        return integral(rates);
    }

    private List<Conveyor> integral(List<Rate> rates) {
        return getAll().stream().filter(conv ->
                compareConveyorAndQuestionnaire.proximity(conv, rates)).collect(Collectors.toList());
    }

    private List<Conveyor> getAll() {
        return adapter.getNearConveyors(new ArrayList<>());
    }
}
