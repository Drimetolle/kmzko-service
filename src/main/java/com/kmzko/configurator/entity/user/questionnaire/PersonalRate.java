package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.domains.AbstractRate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "personal_rate")
@NoArgsConstructor
@Getter
@Setter
public class PersonalRate extends AbstractRate {
}
