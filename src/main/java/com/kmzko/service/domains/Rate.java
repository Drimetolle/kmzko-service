package com.kmzko.service.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String value;
    private String type;

    public Rate(String name, String value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }
}
