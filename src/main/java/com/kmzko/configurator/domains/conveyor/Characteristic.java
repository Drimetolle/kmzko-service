package com.kmzko.configurator.domains.conveyor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
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
    private String mark;
    private String type;

////    @Transient
//    private Unit unit;

    public Characteristic(String name, String mark, String value, String type) {
        this.name = name;
        this.mark = mark;
        this.value = value;
        this.type = type;
        //TODO
//        this.value = String.valueOf(unit.getValue());
//        this.type = unit.getClass().getSimpleName();
    }
}
