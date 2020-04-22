package com.kmzko.configurator.entity.user.conveyor;

import com.kmzko.configurator.entity.AbstractDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personal_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalDetail extends AbstractDetail {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "detail_id", nullable=false)
    private List<PersonalCharacteristic> characteristics;
}
