package com.kmzko.service.utils;

import com.kmzko.service.domains.Rate;
import com.kmzko.service.domains.conveyor.Characteristic;
import com.kmzko.service.domains.conveyor.Conveyor;
import com.kmzko.service.domains.conveyor.Detail;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CompareConveyorAndQuestionnaire {
    private final int bound;
    private final float interval;
    private final Map<String, Float> rates;
    private final Set<String> nodeMarks;

    public boolean proximity(Conveyor conveyor, List<Rate> rates) {
        List<Detail> details = conveyor.getNodes().stream()
                .flatMap(i -> i.getDetails().stream()).collect(Collectors.toList());

        float acc = accumulate(details, rates);

        return acc >= bound;
    }

    public float accumulate(List<Detail> conveyor, List<Rate> rates) {
        float acc = 0;
        for (Rate rate : rates) {
            acc += matchInInterval(conveyor, rate);
        }
        return acc;
    }

    private float matchInInterval(List<Detail> details, Rate rate) {
        float acc = 0;
        for (Detail detail : details) {
            List<Characteristic> characteristics = detail.getCharacteristics().stream()
                    .filter(i -> i.getMark().equals(rate.getType()) && compareTwoRate(i.getValue(), rate.getValue()))
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

    private boolean compareTwoRate(float r1, String r2) {
        try {
            float r2Number = Float.parseFloat(r2);

            return inRangeNumber(r1, r2Number);
        }
        catch (NumberFormatException e) {
            return String.valueOf(r1).equals(r2);
        }
    }

    private boolean inRangeNumber(float x, float y) {
        float rin = 1 - interval;
        float up = x + rin * x;
        float down = x - rin * x;

        return up >= y && down <= y;
    }
}
