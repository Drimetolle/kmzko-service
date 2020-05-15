package com.kmzko.configurator.utils;

import com.kmzko.configurator.dto.conveyor.CharacteristicDto;
import com.kmzko.configurator.dto.conveyor.ConveyorDto;
import com.kmzko.configurator.dto.conveyor.DetailDto;
import com.kmzko.configurator.dto.questionnaire.RateDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CompareConveyorAndQuestionnaire {
    private final int bound;
    private final float interval;
    private final Map<String, Float> rates;

    public boolean proximity(ConveyorDto conveyor, List<RateDto> rates) {
        List<DetailDto> details = conveyor.getNodes().stream()
                .flatMap(i -> i.getDetails().stream()).collect(Collectors.toList());

        float acc = accumulate(details, rates);

        return acc >= bound;
    }

    private float accumulate(List<DetailDto> conveyor, List<RateDto> rates) {
        float acc = 0;
        for (RateDto rate : rates) {
            acc += matchInInterval(conveyor, rate);
        }
        return acc;
    }

    private float matchInInterval(List<DetailDto> details, RateDto rate) {
        float acc = 0;
        for (DetailDto detail : details) {
            List<CharacteristicDto> characteristics = detail.getCharacteristics().stream()
                    .filter(i -> i.getMark().equals(rate.getMark()) && compareTwoRate(i.getValue(), rate.getValue()))
                    .collect(Collectors.toList());

            acc += characteristics.stream().map(i -> rates.get(i.getMark())).reduce(Float::sum).orElse((float) 0);
        }
        return acc;
    }

    private boolean compareTwoRate(String r1, String r2) {
        try {
            float r1Number = Float.parseFloat(r1);
            float r2Number = Float.parseFloat(r2);

            return inRangeNumber(r1Number, r2Number);
        }
        catch (NumberFormatException e) {
            return r1.equals(r2);
        }
    }

    private boolean inRangeNumber(float x, float y) {
        float rin = 1 - interval;
        float up = x + rin * x;
        float down = x - rin * x;

        return up >= y && down <= y;
    }
}
