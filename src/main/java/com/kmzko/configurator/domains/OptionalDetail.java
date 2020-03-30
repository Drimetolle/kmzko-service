package com.kmzko.configurator.domains;

import com.kmzko.configurator.entity.AbstractEntity;
import com.kmzko.configurator.entity.user.PersonalConveyor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "optional_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionalDetail extends AbstractEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "personal_conveyor_id", nullable = false)
    private PersonalConveyor conveyor;
}
