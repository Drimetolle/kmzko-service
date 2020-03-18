package com.kmzko.service.domains.conveyor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String value;
    private String type;

    @Transient
    private Unit unit;

    public Characteristic(String name, Unit unit) {
        this.name = name;
        this.unit = unit;

        //TODO
        this.value = String.valueOf(unit.getValue());
        this.type = unit.getClass().getSimpleName();
    }
}
