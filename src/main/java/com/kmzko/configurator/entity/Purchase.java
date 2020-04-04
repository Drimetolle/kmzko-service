package com.kmzko.configurator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Purchase extends AbstractEntity {
    private String stub;
}
