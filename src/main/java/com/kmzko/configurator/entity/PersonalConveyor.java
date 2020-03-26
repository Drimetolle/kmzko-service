package com.kmzko.configurator.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "personal_conveyor")
@Getter
@Setter
public class PersonalConveyor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
