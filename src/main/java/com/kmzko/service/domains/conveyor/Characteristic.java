package com.kmzko.service.domains.conveyor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Characteristic {
    private String name;
    private Unit unit;

    public Characteristic(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }

    public Characteristic(Unit unit) {
        this.unit = unit;
    }
}
