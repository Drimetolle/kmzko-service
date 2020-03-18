package com.kmzko.service.domains.conveyor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Characteristic> characteristics;

    public Detail(String name, List<Characteristic> characteristics) {
        this.name = name;
        this.characteristics = characteristics;
    }
}
