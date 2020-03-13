package com.kmzko.service.domains.conveyor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Characteristic {
    private String name;
    private Unit unit;

    public Characteristic(Unit unit) {
        this.unit = unit;
    }
}
