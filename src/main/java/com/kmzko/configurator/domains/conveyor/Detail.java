package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Detail extends AbstractEntity {
    private String name;
    private int count;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Characteristic> characteristics;
}
