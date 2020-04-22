package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.entity.AbstractCharacteristic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "characteristic")
@NoArgsConstructor
@Getter
@Setter
public class Characteristic extends AbstractCharacteristic {
}
