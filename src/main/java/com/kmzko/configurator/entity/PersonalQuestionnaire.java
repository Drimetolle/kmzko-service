package com.kmzko.configurator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.Rate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personal_questionnaire")
@Getter
@Setter
public class PersonalQuestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ConveyorType type;
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date utilDate;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rateList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
