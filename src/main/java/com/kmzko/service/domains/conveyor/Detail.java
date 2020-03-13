package com.kmzko.service.domains.conveyor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Detail {
    private String name;
    private List<Characteristic> characteristics;

    public Detail(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
