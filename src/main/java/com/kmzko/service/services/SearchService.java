package com.kmzko.service.services;

import com.kmzko.service.domains.Rate;
import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.repositories.ConveyorRepo;
import com.kmzko.service.services.kmzko.api.AdapterAPI;
import com.kmzko.service.utils.CompareConveyorAndQuestionnaire;
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
