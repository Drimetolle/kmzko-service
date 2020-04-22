package com.kmzko.configurator.entity.user.conveyor;

import com.kmzko.configurator.entity.AbstractCharacteristic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "personal_characteristic")
@NoArgsConstructor
@Getter
@Setter
public class PersonalCharacteristic extends AbstractCharacteristic {
}
