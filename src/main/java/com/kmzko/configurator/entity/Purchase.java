package com.kmzko.configurator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "purchase")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Purchase extends AbstractEntity {
    @NotNull
    private String stub;
}
