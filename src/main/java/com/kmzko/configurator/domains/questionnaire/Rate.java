package com.kmzko.configurator.domains.questionnaire;

import com.kmzko.configurator.entity.AbstractRate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rate")
@NoArgsConstructor
@Getter
@Setter
public class Rate extends AbstractRate {
}
