package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Unit extends AbstractEntity {
    private String name;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "unit_id", nullable=false)
//    private List<Characteristic> characteristics;
}

/*
* type:
* meter
* kilogram
* watt
* cross
* */
