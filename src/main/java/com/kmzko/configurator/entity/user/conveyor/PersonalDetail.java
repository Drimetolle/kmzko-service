package com.kmzko.configurator.entity.user.conveyor;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "personal_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalDetail extends AbstractEntity {
    @NotNull
    private String name;

    @NotNull
    private int count;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "detail_id", nullable=false)
    private List<PersonalCharacteristic> characteristics;
}
