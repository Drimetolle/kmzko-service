package com.kmzko.configurator.services;

import com.kmzko.configurator.dto.conveyor.ConveyorDto;
import com.kmzko.configurator.dto.questionnaire.RateDto;
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

    public List<ConveyorDto> getNearConveyors(List<RateDto> rates) {
        return integral(rates);
    }

    private List<ConveyorDto> integral(List<RateDto> rates) {
        return getAll().stream().filter(conveyor ->
                compareConveyorAndQuestionnaire.proximity(conveyor, rates)).collect(Collectors.toList());
    }

    private List<ConveyorDto> getAll() {
        return adapter.getNearConveyors(new ArrayList<>());
    }
}
