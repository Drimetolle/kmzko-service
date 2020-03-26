package com.kmzko.configurator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.conveyor.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personal_conveyor")
@Getter
@Setter
public class PersonalConveyor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date utilDate;
    @Enumerated(EnumType.STRING)
    private ConveyorType type;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Node> nodes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
